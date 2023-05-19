package com.sonu.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitHubSourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(GitHubSourceConnector.class);
  private GitHubSourceConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    config = new GitHubSourceConnectorConfig(map);

    //TODO: Add things you need to do to setup your connector.
  }

  @Override
  public Class<? extends Task> taskClass() {
    //TODO: Return your task implementation.
    return GitHubSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    //TODO: Define the individual task configurations that will be executed.
    ArrayList<Map<String,String>> configs = new ArrayList(1);
    configs.add(config.originalsStrings());
    return configs;
//    throw new UnsupportedOperationException("This has not been implemented.");
  }

  @Override
  public void stop() {
    //TODO: Do things that are necessary to stop your connector.
  }

  @Override
  public ConfigDef config() {
    return GitHubSourceConnectorConfig.conf();
  }
}
