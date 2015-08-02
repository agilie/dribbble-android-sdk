package com.agilie.dribbblesdk.service.retrofit.services;

import java.util.List;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Followee;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.Team;
import com.agilie.dribbblesdk.domain.User;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface DribbbleUserService {

    /**
     * Get a single user
     *
     * @param userId    User ID to get
     * @param callback  Network operation result
     */
    @GET("/users/{user}")
    void getSingleUser(@Path("user") long userId, Callback<User> callback);

    /**
     * Get the authenticated user
     *
     * @param callback  Network operation result
     */
    @GET("/user")
    void fetchAuthenticatedUser(Callback<User> callback);


    /************************************** USER BUCKETS ****************************************************/

    /**
     * Get user's buckets list
     *
     * @param userId    User ID to get buckets list
     * @param callback  Network operation result
     */
    @GET("/users/{user}/buckets")
    void getUsersBuckets(@Path("user") long userId, Callback<List<Bucket>> callback);

    /**
     * Get authenticated user's buckets list
     *
     * @param callback  Network operation result
     */
    @GET("/user/buckets")
    void getAuthenticatedUsersBuckets(Callback<List<Bucket>> callback);


    /************************************** USER FOLLOWERS **************************************************/

    /**
     * Get user's followers list
     *
     * @param userId    User ID to get followers list
     * @param callback  Network operation result
     */
    @GET("/users/{user}/followers")
    void getUsersFollowers(@Path("user") long userId, Callback<List<Followee>> callback);

    /**
     * Get authenticated user's followers list
     *
     * @param callback  Network operation result
     */
    @GET("/user/followers")
    void getAuthenticatedUsersFollowers(Callback<List<Followee>> callback);

    /**
     * List of users followed by a user
     *
     * @param userId    User ID
     * @param callback  Network operation result
     */
    @GET("/users/{user}/following")
    void getFollowingByUser(@Path("user") long userId, Callback<List<Followee>> callback);

    /**
     * List of users followed by an authenticated user
     *
     * @param callback  Network operation result
     */
    @GET("/user/following")
    void getFollowingByCurrentUser(Callback<List<Followee>> callback);

    /**
     * List of shots for users followed by an authenticated user
     *
     * Listing shots from followed users requires the user to be authenticated
     * with the <u>public</u> scope. Also note that you can not retrieve more than 600 results,
     * regardless of the number requested per page.
     *
     * @param callback  Network operation result
     */
    @GET("/user/following/shots")
    void shotsForUserFollowedByUser(Callback<List<Shot>> callback);

    @GET("/user/following/shots")
    Response shotsForUserFollowedByUser();

    /**
     * Check if you are following the user.
     * Checking for a shot like requires the user to be authenticated.
     *
     * Note that returned result may contain "404 not found" error, if you are not following this user.
     * It's better use synchronous method which returns Response.class.
     *
     * @param userId    User ID to check
     * @param callback  Network operation result
     */
    @GET("/user/following/{user}")
    void checkUserIsFollowed(@Path("user") long userId, Callback<Void> callback);

    @GET("/user/following/{user}")
    Response checkUserIsFollowed(@Path("user") long userId);

    /**
     * Check if one user is following another
     *
     * Note that returned result may contain "404 not found" error, if user does not follow target user.
     * It's better use synchronous method which returns Response.class.
     *
     * @param userId        User ID to be checked
     * @param targetUserId  Target user ID which will draw comparisons
     * @param callback      Network operation result
     */
    @GET("/users/{user}/following/{target_user}")
    void checkUserIsFollowingAnother(@Path("user") long userId, @Path("target_user") long targetUserId, Callback<Void> callback);

    @GET("/users/{user}/following/{target_user}")
    Response checkUserIsFollowingAnother(@Path("user") long userId, @Path("target_user") long targetUserId);

    /**
     * Follow a user.
     * Following a user requires the user to be authenticated with the <u>write</u> scope.
     *
     * The following errors are possible, and will be on the base attribute:
     * You cannot follow yourself.
     * You have been blocked from following this member at their request.
     * You have reached the maximum number of follows allowed.
     *
     * @param userId    User id to follow
     * @param callback  Network operation result
     */
    @PUT("/users/{id}/follow")
    void followUser(@Path("id")long userId, Callback<Void> callback);

    @PUT("/users/{id}/follow")
    Response followUser(@Path("id")long userId);

    /**
     * Unfollow a user.
     * Unfollowing a user requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param userId    User id to unfollow
     * @param callback  Network operation result
     */
    @DELETE("/users/{id}/follow")
    void unfollowUser(@Path("id")long userId, Callback<Void> callback);


    /************************************** USER LIKES ******************************************************/

    /**
     * Get a user’s shot likes list
     *
     * @param userId    User ID to get list shot likes
     * @param callback  Network operation result
     */
    @GET("/users/{user}/likes")
    void getUsersLikes(@Path("user") long userId, Callback<List<Like>> callback);

    /**
     * Get the authenticated user’s shot likes list
     *
     * @param callback  Network operation result
     */
    @GET("/user/likes")
    void getAuthenticatedUsersLikes(Callback<List<Like>> callback);


    /************************************** USER PROJECTS ***************************************************/

    /**
     * Get a user’s projects list
     *
     * @param userId    User ID to get project list
     * @param callback  Network operation result
     */
    @GET("/users/{user}/projects")
    void getUsersProjects(@Path("user") long userId, Callback<List<Project>> callback);

    /**
     * Get the authenticated user’s projects list
     *
     * @param callback  Network operation result
     */
    @GET("/user/projects")
    void getAuthenticatedUsersProjects(Callback<List<Project>> callback);


    /************************************** USER SHOTS ******************************************************/

    /**
     * Get a user’s shots list
     *
     * @param userId    User ID to get shot list
     * @param callback  Network operation result
     */
    @GET("/users/{user}/shots")
    void getUsersShots(@Path("user") long userId, Callback<List<Shot>> callback);

    /**
     * Get authenticated user's shots list
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Number of shot per page
     * @param callback Network operation result
     */
    @GET("/user/shots")
    void getAuthenticatedUsersShots(@Query("page") int page, @Query("per_page") int perPage,  Callback<List<Shot>> callback);

    @GET("/user/shots")
    void getAuthenticatedUsersShots(@Query("page") int page,  Callback<List<Shot>> callback);


    /************************************** USER TEAMS ******************************************************/

    /**
     * Get a user’s teams list
     *
     * @param userId    User ID to get shot list
     * @param callback  Network operation result
     */
    @GET("/users/{user}/teams")
    void getUsersTeams(@Path("user") long userId, Callback<List<Team>> callback);

    /**
     * Get authenticated user's teams list
     *
     * @param page      Page number, used to receive result partially by pages.
     *                  Increase this value by 1 for each next request
     * @param callback  Network operation result
     */
    @GET("/user/teams")
    void getAuthenticatedUsersTeams(@Query("page") int page, Callback<List<Team>> callback);
}
