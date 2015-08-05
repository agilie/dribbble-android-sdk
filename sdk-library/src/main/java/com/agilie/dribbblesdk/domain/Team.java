package com.agilie.dribbblesdk.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Team implements Serializable {

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
        "web" : "http://dribbble.com",
                "twitter" : "https://twitter.com/dribbble"
    },
    */

    @SerializedName("buckets_count")
    private long bucketsCount;

    @SerializedName("comments_received_count")
    private long commentsReceivedCount;

    @SerializedName("followers_count")
    private long followersCount;

    @SerializedName("followings_count")
    private long followingsCount;

    @SerializedName("likes_count")
    private long likesCount;

    @SerializedName("likes_received_count")
    private long likesReceivedCount;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
            result.put("buckets_count", bucketsCount);
            result.put("comments_received_count", commentsReceivedCount);
            result.put("followers_count", followersCount);
            result.put("followings_count", followingsCount);
            result.put("likes_count", likesCount);
            result.put("likes_received_count", likesReceivedCount);
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
