package agilie.dribbblesdk.service.retrofit.services;

import java.lang.reflect.Member;
import java.util.List;

import agilie.dribbblesdk.domain.Project;
import agilie.dribbblesdk.domain.Shot;
import agilie.dribbblesdk.domain.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by serg on 7/9/15.
 */
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
