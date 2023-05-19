package com.sonu.kafka;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.sonu.kafka.GitHubSourceConnectorConfig.*;
import static org.junit.Assert.assertEquals;

public class GitHubSourceConnectorTest {
  private Map<String,String> initialConfig() {
    Map<String,String> properties = new HashMap<>();
    properties.put(OWNER_CONFIG,"foo");
    properties.put(REPO_CONFIG,"bar");
    properties.put(SINCE_CONFIG, "2023-01-01T00:00:00Z");
    properties.put(BATCH_SIZE_CONFIG, "100");
    properties.put(TOPIC_CONFIG,"github-issues");
    return properties;
  }

  @Test
  public void taskConfigShouldReturnOneTaskConfig() {
    GitHubSourceConnector gitHubSourceConnector = new GitHubSourceConnector();
    gitHubSourceConnector.start(initialConfig());
    assertEquals(gitHubSourceConnector.taskConfigs(1).size(),1);
    assertEquals(gitHubSourceConnector.taskConfigs(10).size(), 1);
  }
}
