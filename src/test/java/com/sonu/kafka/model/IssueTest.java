package com.sonu.kafka.model;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IssueTest {
    String issueStr = "{\n" +
            "  \"url\": \"https://api.github.com/repos/apache/kafka/issues/2800\",\n" +
            "  \"repository_url\": \"https://api.github.com/repos/apache/kafka\",\n" +
            "  \"labels_url\": \"https://api.github.com/repos/apache/kafka/issues/2800/labels{/name}\",\n" +
            "  \"comments_url\": \"https://api.github.com/repos/apache/kafka/issues/2800/comments\",\n" +
            "  \"events_url\": \"https://api.github.com/repos/apache/kafka/issues/2800/events\",\n" +
            "  \"html_url\": \"https://github.com/apache/kafka/pull/2800\",\n" +
            "  \"id\": 219155037,\n" +
            "  \"number\": 2800,\n" +
            "  \"title\": \"added interface to allow producers to create a ProducerRecord without…\",\n" +
            "  \"user\": {\n" +
            "    \"login\": \"simplesteph\",\n" +
            "    \"id\": 20851561,\n" +
            "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/20851561?v=3\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/simplesteph\",\n" +
            "    \"html_url\": \"https://github.com/simplesteph\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/simplesteph/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/simplesteph/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/simplesteph/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/simplesteph/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/simplesteph/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/simplesteph/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/simplesteph/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/simplesteph/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/simplesteph/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  },\n" +
            "  \"labels\": [ \n" +
            "       { \n" +
            "       \"id\": 208045946, \n" +
            "       \"node_id\": \"MDU6TGFiZWwyMDgwNDU5NDY=\", \n" +
            "       \"url\": \"https://api.github.com/repos/octocat/Hello-World/labels/bug\", \n" +
            "       \"name\": \"bug\", \n" +
            "       \"description\": \"Something isn't working\", \n" +
            "       \"color\": \"f29513\", \n" +
            "       \"default\": true \n" +
            "       } \n" +
            "   ], \n" +
            "  \"state\": \"closed\",\n" +
            "  \"locked\": false,\n" +
            "  \"assignee\": null,\n" +
            "  \"assignees\": [],\n" +
            "  \"milestone\": null,\n" +
            "  \"comments\": 12,\n" +
            "  \"created_at\": \"2017-04-04T06:47:09Z\",\n" +
            "  \"updated_at\": \"2017-04-19T22:36:21Z\",\n" +
            "  \"closed_at\": \"2017-04-19T22:36:21Z\",\n" +
            "  \"pull_request\": {\n" +
            "    \"url\": \"https://api.github.com/repos/apache/kafka/pulls/2800\",\n" +
            "    \"html_url\": \"https://github.com/apache/kafka/pull/2800\",\n" +
            "    \"diff_url\": \"https://github.com/apache/kafka/pull/2800.diff\",\n" +
            "    \"patch_url\": \"https://github.com/apache/kafka/pull/2800.patch\"\n" +
            "  },\n" +
            "  \"body\": \"… specifying a partition, making it more obvious that the parameter partition can be null\"\n" +
            "}";
    JSONObject issueJsonObj = new JSONObject(issueStr);

    @Test
    public void parseJson() {
        Issue issue = Issue.fromJson(issueJsonObj);
        assertEquals(issue.getNumber(), (Integer) 2800);
        assertEquals(issue.getCreatedAt().toString(), "2017-04-04T06:47:09Z");
        assertEquals(issue.getUpdatedAt().toString(), "2017-04-19T22:36:21Z");
        assertEquals(issue.getUrl(), "https://api.github.com/repos/apache/kafka/issues/2800");
        assertEquals(issue.getTitle(), "added interface to allow producers to create a ProducerRecord without…");
        assertEquals(issue.getState(), "closed");

        //user
        assertEquals(issue.getUser().getLogin(),"simplesteph");
        assertEquals(issue.getUser().getId(), (Integer) 20851561 );

        //pull request
        assertEquals(issue.getPullRequest().getUrl(), "https://api.github.com/repos/apache/kafka/pulls/2800");
        assertNotEquals(issue.getPullRequest().getUrl(), "https://api.github.com/repos/apache/kafka/pulls/2900");

        //labels
//        assertEquals(issue.getLabels().get(0).getName(),"bug");

    }

}
