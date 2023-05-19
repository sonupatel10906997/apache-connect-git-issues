package com.sonu.kafka.model;

import org.json.JSONObject;

import java.time.Instant;
import java.util.List;

import static com.sonu.kafka.GitHubSchemas.*;

public class Issue {
    /**
     * we should consider using all the fields but here for learning purpose i am only selecting the fields which
     * which are in our schema
     * */
    private Instant createdAt;
    private Instant updatedAt;
    private Integer number;
    private String url;
    private String title;
    private String state;

    private List<Label> labels = null;

    private User user;
    private PullRequest pullRequest;

    public Issue(){}

    public void Issue(Instant createdAt, Instant updatedAt, Integer number, String url, String title, String state, List<Label> labels, User user, PullRequest pullRequest){
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
        this.url = url;
        this.title = title;
        this.state = state;
        this.labels = labels;
        this.user = user;
        this.pullRequest = pullRequest;
    }

    public Instant getCreatedAt(){ return createdAt; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
    public Issue withCreatedAt(Instant createdAt){ this.createdAt = createdAt;
        return this;
    }
    public Instant getUpdatedAt(){ return updatedAt; }
    public void setUpdatedAt(Instant updatedAt){ this.updatedAt = updatedAt; }
    public Issue withUpdatedAt(Instant updatedAt){ this.updatedAt = updatedAt;
        return this;
    }
    public Integer getNumber(){ return number; }
    public void setNumber(Integer number) { this.number = number; }
    public Issue withNumber(Integer number) { this.number = number;
        return this;
    }

    public String getUrl(){ return url; }
    public void setUrl( String url) { this.url = url; }
    public Issue withUrl( String url) { this.url = url;
        return this;
    }
    public String getTitle(){ return title; }
    public void setTitle(String title) { this.title = title; }
    public Issue withTitle(String title) { this.title = title;
        return this;
    }
    public String getState(){ return state; }
    public void setState(String state) { this.state = state; }
    public Issue withState(String state) { this.state = state;
        return this;
    }

    public List<Label> getLabels(){ return labels; }
    public void setLabels(List<Label> labels) { this.labels = labels; }
    public Issue withLabels(List<Label> labels) { this.labels = labels;
        return this;
    }


    public User getUser(){ return user; }
    public void setUser(User user) { this.user = user; }
    public Issue withUser(User user) { this.user = user;
        return this;
    }

    public PullRequest getPullRequest() { return pullRequest; }
    public void setPullRequest(PullRequest pullRequest) { this.pullRequest = pullRequest; }
    public Issue withPullRequest(PullRequest pullRequest) { this.pullRequest = pullRequest;
        return this;
    }

    public static Issue fromJson(JSONObject jsonObject) {
        Issue issue = new Issue();
        issue.withNumber(jsonObject.getInt(NUMBER_FIELD));
        issue.withCreatedAt(Instant.parse(jsonObject.getString(CREATED_AT_FIELD)));
        issue.withUpdatedAt(Instant.parse(jsonObject.getString(UPDATE_AT_FIELD)));
        issue.withUrl(jsonObject.getString(URL_FIELD));
        issue.withTitle(jsonObject.getString(TITLE_FIELD));
        issue.withState(jsonObject.getString(STATE_FIELD));

        User  user = User.fromJson(jsonObject.getJSONObject("user"));
        issue.withUser(user);

        if (jsonObject.has("pull_request")) {
            PullRequest pullRequest1 = PullRequest.fromJson(jsonObject.getJSONObject("pull_request"));
            issue.withPullRequest(pullRequest1);
        }

//        List<Label> label = Label.fromJson(jsonObject.getJSONArray("labels"));
//        issue.withLabels(label);

        return issue;
    }




}
