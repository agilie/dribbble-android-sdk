package com.agilie.dribbblesdk.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tatyanasoldatkina on 3/24/15.
 */
public class Followee implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("followee")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
