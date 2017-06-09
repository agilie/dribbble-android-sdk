package com.agilie.dribbblesdk.service.retrofit.services;

import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by serg on 7/9/15.
 */
public interface DribbbleTeamsService {

    /**
     * List a team’s members
     *
     * @param teamId    Team ID with the list of the members
     *
     * @return          Network operation result
     */

    @GET("teams/{team}/members")
    Call<List<User>> getTeamMembersList(@Path("team") long teamId);

    /**
     * List shots for a team
     *
     * @param teamId    Team ID
     *
     * @return          Network operation result
     */

    @GET("teams/{team}/shots")
    Call<List<Shot>> getTeamShotsList(@Path("team") long teamId);
}
