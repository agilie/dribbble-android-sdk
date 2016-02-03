package com.agilie.dribbblesdk.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Date;

/**
 * Created by tatyanasoldatkina on 3/26/15.
 */
public class Rebound implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("images")
    private Images images;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("attachments_count")
    private int attachmentsCount;

    @SerializedName("rebounds_count")
    private int reboundsCount;

    @SerializedName("buckets_count")
    private int bucketsCount;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("attachments_url")
    private String attachmentsUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("likes_url")
    private String  likesUrl;

    @SerializedName("projects_url")
    private String projectsUrl;

    @SerializedName("rebounds_url")
    private String reboundsUrl;

    @SerializedName("rebound_source_url")
    private String reboundSourceUrl;

    @SerializedName("tags")
    private Array tags;

    @SerializedName("user")
    private User user;

    @SerializedName("team")
    private Team team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(int attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public int getReboundsCount() {
        return reboundsCount;
    }

    public void setReboundsCount(int reboundsCount) {
        this.reboundsCount = reboundsCount;
    }

    public int getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(int bucketsCount) {
        this.bucketsCount = bucketsCount;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAttachmentsUrl() {
        return attachmentsUrl;
    }

    public void setAttachmentsUrl(String attachmentsUrl) {
        this.attachmentsUrl = attachmentsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getProjectsUrl() {
        return projectsUrl;
    }

    public void setProjectsUrl(String projectsUrl) {
        this.projectsUrl = projectsUrl;
    }

    public String getReboundsUrl() {
        return reboundsUrl;
    }

    public void setReboundsUrl(String reboundsUrl) {
        this.reboundsUrl = reboundsUrl;
    }

    public String getReboundSourceUrl() {
        return reboundSourceUrl;
    }

    public void setReboundSourceUrl(String reboundSourceUrl) {
        this.reboundSourceUrl = reboundSourceUrl;
    }

    public Array getTags() {
        return tags;
    }

    public void setTags(Array tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}