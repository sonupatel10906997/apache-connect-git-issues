package com.sonu.kafka;

import org.junit.Test;

public class GitHubSourceConnectorConfigTest {
  @Test
  public void doc() {
    System.out.println(GitHubSourceConnectorConfig.conf().toRst());
  }
}