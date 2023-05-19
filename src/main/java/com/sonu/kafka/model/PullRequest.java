package com.sonu.kafka.model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.sonu.kafka.GitHubSchemas.PR_HTML_URL_FIELD;
import static com.sonu.kafka.GitHubSchemas.PR_URL_FIELD;

public class PullRequest {

    private String url;
    private String htmlUrl;
    private String diffUrl;
    private String patchUrl;
    private Map<String,Object> additionalProperties = new HashMap<>();

    public PullRequest(){}

    public void PullRequest(String url, String htmlUrl, String diffUrl, String patchUrl)
    {
        this.url= url;
        this.htmlUrl = htmlUrl;
        this.diffUrl = diffUrl;
        this.patchUrl = patchUrl;
    }

    public String getUrl(){ return url;}
    public void setUrl(String url) { this.url = url;}
    public PullRequest withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getHtmlUrl(){ return htmlUrl;}
    public void setHtmlUrl(String htmlUrl) { this.htmlUrl = htmlUrl;}
    public PullRequest withHtmlUrl(String htmlUrl){
        this.htmlUrl = htmlUrl;
        return this;
    }

    public String getDiffUrl(){ return diffUrl;}
    public void setDiffUrl(String diffUrl) { this.diffUrl = diffUrl;}
    public PullRequest withDiffUrl(String diffUrl){
        this.diffUrl = diffUrl;
        return this;
    }

    public String getPatchUrl(){ return patchUrl;}
    public void setPatchUrl(String patchUrl) { this.patchUrl = patchUrl; }
    public PullRequest withPatchUrl(String patchUrl){
        this.patchUrl = patchUrl;
        return this;
    }

    public Map<String, Object> getAdditionalProperties(){ return additionalProperties; }
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public PullRequest withAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public static PullRequest fromJson(JSONObject jsonObject) {
        return new PullRequest()
                .withUrl(jsonObject.getString(PR_URL_FIELD))
                .withHtmlUrl(jsonObject.getString(PR_HTML_URL_FIELD));
    }
}
