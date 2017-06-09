package com.agilie.dribbblesdk.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by tatyanasoldatkina on 3/26/15.
 */
public class Images implements Serializable {

    @SerializedName("hidpi")
    private String hidpi;

    @SerializedName("normal")
    private String normal;

    @SerializedName("teaser")
    private String teaser;

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
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

    public String toGson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        try {
            result.put("hidpi", hidpi);
            result.put("normal", normal);
            result.put("teaser", teaser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
