package com.agilie.dribbblesdk.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tatyanasoldatkina on 3/25/15.
 */
public class Like implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("user")
    private User user;

    @SerializedName("shot")
    private Shot shot;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
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
