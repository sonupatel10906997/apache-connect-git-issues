package com.sonu.kafka.model;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    String userStr = "{ \n" +
              "\"login\": \"octocat\", \n" +
              "\"id\": 1, \n" +
              "\"node_id\": \"MDQ6VXNlcjE=\", \n" +
              "\"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\", \n" +
              "\"gravatar_id\": \"\", \n" +
              "\"url\": \"https://api.github.com/users/octocat\", \n" +
              "\"html_url\": \"https://github.com/octocat\", \n" +
              "\"followers_url\": \"https://api.github.com/users/octocat/followers\", \n" +
              "\"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\", \n" +
              "\"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\", \n" +
              "\"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\", \n" +
              "\"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\", \n" +
              "\"organizations_url\": \"https://api.github.com/users/octocat/orgs\", \n" +
              "\"repos_url\": \"https://api.github.com/users/octocat/repos\", \n" +
              "\"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\", \n" +
              "\"received_events_url\": \"https://api.github.com/users/octocat/received_events\", \n" +
              "\"type\": \"User\", \n" +
              "\"site_admin\": false \n" +
              "}";

    private JSONObject userJson = new JSONObject(userStr);


    @Test
    public void canParseJson(){
        User user = User.fromJson(userJson);
        assertEquals(user.getUrl(), "https://api.github.com/users/octocat");
        assertEquals(user.getHtmlUrl(), "https://github.com/octocat");
        assertEquals(user.getId(),(Integer) 1);
        assertEquals(user.getLogin(), "octocat");
    }
}
