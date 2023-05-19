package com.sonu.kafka.model;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import static com.sonu.kafka.GitHubSchemas.*;

public class User {
    private String login;
    private Integer id;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionUrl;
    private String organizationUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private String siteAdmin;
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
    * No args constructor for use in serialization
    *
     */
    public User(){

    }

    public User(String login, Integer id, String avatarUrl, String gravatarId,
                String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionUrl, String organizationUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, String siteAdmin) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionUrl = subscriptionUrl;
        this.organizationUrl = organizationUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }

    public String getLogin() {return login;}

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * sets the login and return the object for nesting
     * */
    public User withLogin(String login) {
        this.login = login;
        return this;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getAvatarUrl() {return avatarUrl;}

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public User withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
    public String getGravatarId() {return gravatarId;}

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public User withGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {
        this.url = url;
    }

    public User withUrl(String url) {
        this.url = url;
        return this;
    }
    public String getHtmlUrl() {return htmlUrl;}

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public User withHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public String getFollowersUrl() {return followersUrl;}

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public User withFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
        return this;
    }
    public String getFollowingUrl() {return followingUrl;}

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public User withFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
        return this;
    }
    public String getGistsUrl() {return gistsUrl;}

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public User withGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
        return this;
    }
    public String getStarredUrl() {return starredUrl;}

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public User withStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
        return this;
    }
    public String getSubscriptionUrl() {return subscriptionUrl;}

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public User withSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
        return this;
    }
    public String getOrganizationUrl() {return organizationUrl;}

    public void setOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl;
    }

    public User withOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl;
        return this;
    }
    public String getReposUrl() {return reposUrl;}

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public User withReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
        return this;
    }

    public String getEventsUrl() {return eventsUrl;}

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public User withEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
        return this;
    }
    public String getReceivedEventsUrl() {return receivedEventsUrl;}

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public User withReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
        return this;
    }
    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }

    public User withType(String type) {
        this.type = type;
        return this;
    }

    public String getSiteAdmin() {return siteAdmin;}

    public void setSiteAdmin(String siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public User withSiteAdmin(String siteAdmin) {
        this.siteAdmin = siteAdmin;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {return additionalProperties;}
    public void setAdditionalProperties(String name, Object value){
        this.additionalProperties.put(name,value);
    }

    public User withAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public static User fromJson(JSONObject jsonObject){
        User user = new User();
        user.setUrl(jsonObject.getString(USER_URL_FIELD));
        user.setHtmlUrl(jsonObject.getString(USER_HTML_URL_FIELD));
        user.setId(jsonObject.getInt(USER_ID_FIELD));
        user.setLogin(jsonObject.getString(USER_LOGIN_FIELD));
        return user;

    }


}
