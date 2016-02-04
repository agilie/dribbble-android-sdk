package com.agilie.dribbblesdk.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by serg on 3/19/15.
 */
public class User implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String userName;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("bio")
    private String bio;

    @SerializedName("location")
    private String location;

    /*
    "links" : {
        "web" : "http://simplebits.com",
                "twitter" : "https://twitter.com/simplebits"
    },
    */
    @SerializedName("links")
    private Map<String, String> links;

    @SerializedName("buckets_count")
    private long bucketsCount;

    @SerializedName("comments_received_count")
    private long commentsReceivedCount;

    @SerializedName("followers_count")
    private long followersCount;

    @SerializedName("followings_count")
    private long followingCount;

    @SerializedName("likes_count")
    private long likesCount;

    @SerializedName("likes_received_count")
    private long likesReceivedCount;

    @SerializedName("projects_count")
    private long projectsCount;

    @SerializedName("rebounds_received_count")
    private long reboundsReceivedCount;

    @SerializedName("shots_count")
    private long shotsCount;

    @SerializedName("teams_count")
    private long teamsCount;

    @SerializedName("can_upload_shot")
    private boolean canUploadShot;

    @SerializedName("type")
    private String type;

    @SerializedName("pro")
    private boolean pro;

    @SerializedName("buckets_url")
    private String bucketsUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("likes_url")
    private String likesUrl;

    @SerializedName("shots_url")
    private String shotsUrl;

    @SerializedName("teams_url")
    private String teamsUrl;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public long getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(long bucketsCount) {
        this.bucketsCount = bucketsCount;
    }

    public long getCommentsReceivedCount() {
        return commentsReceivedCount;
    }

    public void setCommentsReceivedCount(long commentsReceivedCount) {
        this.commentsReceivedCount = commentsReceivedCount;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(long followingCount) {
        this.followingCount = followingCount;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public long getLikesReceivedCount() {
        return likesReceivedCount;
    }

    public void setLikesReceivedCount(long likesReceivedCount) {
        this.likesReceivedCount = likesReceivedCount;
    }

    public long getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(long projectsCount) {
        this.projectsCount = projectsCount;
    }

    public long getReboundsReceivedCount() {
        return reboundsReceivedCount;
    }

    public void setReboundsReceivedCount(long reboundsReceivedCount) {
        this.reboundsReceivedCount = reboundsReceivedCount;
    }

    public long getShotsCount() {
        return shotsCount;
    }

    public void setShotsCount(long shotsCount) {
        this.shotsCount = shotsCount;
    }

    public long getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(long teamsCount) {
        this.teamsCount = teamsCount;
    }

    public boolean isCanUploadShot() {
        return canUploadShot;
    }

    public void setCanUploadShot(boolean canUploadShot) {
        this.canUploadShot = canUploadShot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public String getBucketsUrl() {
        return bucketsUrl;
    }

    public void setBucketsUrl(String bucketsUrl) {
        this.bucketsUrl = bucketsUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getShotsUrl() {
        return shotsUrl;
    }

    public void setShotsUrl(String shotsUrl) {
        this.shotsUrl = shotsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        try {
            result.put("id", id);
            result.put("name", name);
            result.put("username", userName);
            result.put("html_url", htmlUrl);
            result.put("avatar_url", avatarUrl);
            result.put("bio", bio);
            result.put("location", location);
            result.put("links", links);
            result.put("buckets_count", bucketsCount);
            result.put("comments_received_count", commentsReceivedCount);
            result.put("followers_count", followersCount);
            result.put("followings_count", followingCount);
            result.put("likes_count", likesCount);
            result.put("likes_received_count", likesReceivedCount);
            result.put("projects_count", projectsCount);
            result.put("rebounds_received_count", reboundsReceivedCount);
            result.put("shots_Count", shotsCount);
            result.put("teams_Count", teamsCount);
            result.put("can_upload_shot", canUploadShot);
            result.put("type", type);
            result.put("pro", pro);
            result.put("buckets_url", bucketsUrl);
            result.put("followers_url", followersUrl);
            result.put("following_url", followingUrl);
            result.put("likes_url", likesUrl);
            result.put("shots_url", shotsUrl);
            result.put("teams_url", teamsUrl);
            result.put("created_at", createdAt);
            result.put("updated_at", updatedAt);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
    public String toGson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        try {
            return toJson().toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
