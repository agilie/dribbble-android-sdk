package com.agilie.dribbblesdk.service.retrofit.services;

import java.util.List;

import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DribbbleTeamsService {

    /**
     * List a team’s members
     *
     * @param teamId    Team ID with the list of the members
     * @param callback  Network operation result
     */

    @GET("/teams/{team}/members")
    void getTeamMembersList(@Path("team") long teamId, Callback<List<User>> callback);

    /**
     * List shots for a team
     *
     * @param teamId    Team ID
     * @param callback  Network operation result
     */

    @GET("/teams/{team}/shots")
    void getTeamShotsList(@Path("team") long teamId, Callback<List<Shot>> callback);
}
