package com.sonu.kafka;

import com.sonu.kafka.model.PullRequest;
import com.sonu.kafka.model.User;
import com.sonu.kafka.model.Issue;
import com.sonu.kafka.utils.DateUtils;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.apache.kafka.connect.data.Struct;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.time.Instant;
import java.util.*;

import static com.sonu.kafka.GitHubSchemas.*;

public class GitHubSourceTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(GitHubSourceTask.class);
  GitHubSourceConnectorConfig config;
  protected Instant nextQuerySince;
  protected Integer lastIssueNumber;
  protected Integer nextPageToVisit = 1;
  protected Instant lastUpdatedAt;

  GitHubAPIHttpClient gitHubAPIHttpClient;

  @Override
  public String version() {

    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    //TODO: Do things here that are required to start your task. This could be open a connection to a database, etc.
    config = new GitHubSourceConnectorConfig(map);
    //TODO: reads the sourcePartition and its last read data from context
    initializeVariables();
    gitHubAPIHttpClient = new GitHubAPIHttpClient(config);
  }

  private void initializeVariables(){
    Map<String , Object> lastSourceOffset = null;
    lastSourceOffset = context.offsetStorageReader().offset(sourcePartition());
    if (lastSourceOffset == null) {
      //we haven't fetched anything yet, so we initialize to 7 days ago
      nextQuerySince = config.getSinceTimestampConfig();
      lastIssueNumber = -1;
    } else {
      Object updatedAt = lastSourceOffset.get(UPDATE_AT_FIELD);
      Object nextPage = lastSourceOffset.get(NEXT_PAGE_FIELD);
      Object lastNumber = lastSourceOffset.get(NUMBER_FIELD);
      if (updatedAt !=null && (updatedAt instanceof String)){
        nextQuerySince = Instant.parse(updatedAt.toString());

      }
      if (nextPage !=null && (nextPage instanceof String)) {
        nextPageToVisit = Integer.valueOf((String) nextPage);
      }
      if ( lastNumber !=null && (lastNumber instanceof String)){
        lastIssueNumber = Integer.valueOf((String) lastNumber);
      }
    }

  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    //TODO: Create SourceRecord objects that will be sent the kafka cluster.
    gitHubAPIHttpClient.sleepIfNeed();

    // fetch data
    final ArrayList<SourceRecord> records = new ArrayList<>();
    try {
      JSONArray  issues = gitHubAPIHttpClient.getNextIssues(nextPageToVisit, nextQuerySince);
      // we will count how many results we get with i
      int i = 0;
      for (Object obj: issues) {
        Issue issue = Issue.fromJson((JSONObject) obj);
        SourceRecord sourceRecord = generateSourceRecord(issue);
        records.add(sourceRecord);
        i += 1;
        lastUpdatedAt = issue.getUpdatedAt();
      }

      if(i > 0) {
        log.info(String.format("Fetched %s records(s)", i));
      }
      if(i == 100) {
        // we have received full batch of 100 records, fetch the next batch
        nextPageToVisit += 1;
      }
      else { // if the batch is less than 100, then we fetch next records after + 1 sec
        nextQuerySince = lastUpdatedAt.plusSeconds(1);
        nextPageToVisit = 1;
        gitHubAPIHttpClient.sleep();
      }
      return records;

    } catch (ConnectException e) {
      throw new RuntimeException(e);
    }
  }

  private SourceRecord generateSourceRecord(Issue issue){
    return new SourceRecord( sourcePartition(),
            sourceOffsets(issue.getUpdatedAt()),
            config.getTopicConfig(),
            null, // partition will never be inferred by schema
            KEY_SCHEMA,
            buildRecordKey(issue),
            VALUE_SCHEMA,
            buildRecordValue(issue),
            issue.getUpdatedAt().toEpochMilli()
            );
  }

  @Override
  public void stop() {
    //TODO: Do whatever is required to stop your task.
  }

  private Map<String,String> sourcePartition() {
    Map<String,String> map = new HashMap<>();
    map.put(OWNER_FIELD, config.getOwnerConfig());
    map.put(REPOSITORY_FIELD, config.getRepoConfig());
    return map;
  }

  private Map<String,String> sourceOffsets(Instant updatedAt) {
    Map<String,String> map = new HashMap<>();
    map.put(UPDATE_AT_FIELD, DateUtils.MaxInstant(updatedAt, nextQuerySince).toString());
    map.put(NEXT_PAGE_FIELD, nextPageToVisit.toString());
    return map;
  }

  private Struct buildRecordKey( Issue issue) {
    // Key Schema
    Struct key = new Struct(KEY_SCHEMA)
            .put(OWNER_FIELD, config.getOwnerConfig())
            .put(REPOSITORY_FIELD, config.getRepoConfig())
            .put(NUMBER_FIELD, issue.getNumber());

    return key;
  }

  private Struct buildRecordValue(Issue issue) {
//    .field(USER_URL_FIELD, Schema.STRING_SCHEMA)
//            .field(USER_HTML_URL_FIELD, Schema.STRING_SCHEMA)
//            .field(USER_ID_FIELD, Schema.INT32_SCHEMA)
//            .field(USER_LOGIN_FIELD, Schema.STRING_SCHEMA)
    Struct value = new Struct(VALUE_SCHEMA)
            .put(URL_FIELD, issue.getUrl())
            .put(TITLE_FIELD, issue.getTitle())
            .put(CREATED_AT_FIELD, Date.from(issue.getCreatedAt()))
            .put(UPDATE_AT_FIELD, Date.from(issue.getUpdatedAt()))
            .put(NUMBER_FIELD, issue.getNumber())
            .put(STATE_FIELD, issue.getState());

    User user = issue.getUser();
    Struct userStruct = new Struct(USER_SCHEMA)
            .put(USER_URL_FIELD, user.getUrl())
            .put(USER_ID_FIELD, user.getId())
            .put(USER_LOGIN_FIELD, user.getLogin());

    value.put(USER_FIELD, userStruct);

    PullRequest pullRequest = issue.getPullRequest();
    if (pullRequest != null) {
      Struct prStruct = new Struct(PR_SCHEMA)
              .put(PR_URL_FIELD, pullRequest.getUrl())
              .put(PR_HTML_URL_FIELD, pullRequest.getHtmlUrl());

      value.put(PR_FIELD, prStruct);
    }

    return value;
  }

}