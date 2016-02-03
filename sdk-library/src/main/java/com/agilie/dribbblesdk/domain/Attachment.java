package com.agilie.dribbblesdk.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tatyanasoldatkina on 3/26/15.
 */
public class Attachment implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("size")
    private long size;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("created_at")
    private Date createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
