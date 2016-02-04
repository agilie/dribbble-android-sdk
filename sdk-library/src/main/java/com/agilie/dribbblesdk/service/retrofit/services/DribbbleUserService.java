package com.agilie.dribbblesdk.service.retrofit.services;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Followee;
import com.agilie.dribbblesdk.domain.Follower;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.Team;
import com.agilie.dribbblesdk.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DribbbleUserService {

    /**
     * Get a single user
     *
     * @param userId    User ID to get
     *
     * @return          Network operation result
     */
    @GET("users/{user}")
    Call<User> getSingleUser(@Path("user") long userId);

    /**
     * Get the authenticated user
     *
     * @return           Network operation result
     */
    @GET("user")
    Call<User> fetchAuthenticatedUser();


    /************************************** USER BUCKETS ****************************************************/

    /**
     * Get user's buckets list
     *
     * @param userId    User ID to get buckets list
     *
     * @return          Network operation result
     */
    @GET("users/{user}/buckets")
    Call<List<Bucket>> getUsersBuckets(@Path("user") long userId);

    /**
     * Get authenticated user's buckets list
     *
     * @return          Network operation result
     */
    @GET("user/buckets")
    Call<List<Bucket>> getAuthenticatedUsersBuckets();


    /************************************** USER FOLLOWERS **************************************************/

    /**
     * Get user's followers list
     *
     * @param userId    User ID to get followers list
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Followers count per one page
     *
     * @return         Network operation result
     */
    @GET("users/{user}/followers")
    Call<List<Follower>> getUsersFollowers(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * Get authenticated user's followers list
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Followers count per one page
     *
     * @return         Network operation result
     */
    @GET("user/followers")
    Call<List<Follower>> getAuthenticatedUsersFollowers(@Query("page") int page, @Query("per_page") int perPage);

    /**
     * List of users followed by a user
     *
     * @param userId    User ID
     *
     * @return          Network operation result
     */
    @GET("users/{user}/following")
    Call<List<Followee>> getFollowingByUser(@Path("user") long userId);

    /**
     * List of users followed by a user
     *
     * @param userId    User ID to get followers list
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Followers count per one page
     *
     * @return         Network operation result
     */
    @GET("users/{user}/following")
    Call<List<Followee>> getFollowingByUser(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * List of users followed by an authenticated user
     *
     * @return          Network operation result
     */
    @GET("user/following")
    Call<List<Followee>> getFollowingByCurrentUser();

    /**
     * List of shots for users followed by an authenticated user
     *
     * Listing shots from followed users requires the user to be authenticated
     * with the <u>public</u> scope. Also note that you can not retrieve more than 600 results,
     * regardless of the number requested per page.
     *
     * @return          Network operation result
     */
    @GET("user/following/shots")
    Call<List<Shot>> shotsForUserFollowedByUser();

    /**
     * Check if you are following the user.
     * Checking for a shot like requires the user to be authenticated.
     *
     * Note that returned result may contain "404 not found" error, if you are not following this user.
     * It's better use synchronous method which returns Response.class.
     *
     * @param userId    User ID to check
     *
     * @return          Network operation result
     */
    @GET("user/following/{user}")
    Call<Void> checkUserIsFollowed(@Path("user") long userId);

    /**
     * Check if one user is following another
     *
     * Note that returned result may contain "404 not found" error, if user does not follow target user.
     * It's better use synchronous method which returns Response.class.
     *
     * @param userId        User ID to be checked
     * @param targetUserId  Target user ID which will draw comparisons
     *
     * @return              Network operation result
     */
    @GET("users/{user}/following/{target_user}")
    Call<Void> checkUserIsFollowingAnother(@Path("user") long userId, @Path("target_user") long targetUserId);

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
     *
     * @return          Network operation result
     */
    @PUT("users/{id}/follow")
    Call<Void> followUser(@Path("id") long userId);

    /**
     * Unfollow a user.
     * Unfollowing a user requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param userId    User id to unfollow
     *
     * @return          Network operation result
     */
    @DELETE("users/{id}/follow")
    Call<Void> unfollowUser(@Path("id") long userId);


    /************************************** USER LIKES ******************************************************/

    /**
     * Get a user’s shot likes list
     *
     * @param userId    User ID to get list shot likes
     *
     * @return          Network operation result
     */
    @GET("users/{user}/likes")
    Call<List<Like>> getUsersLikes(@Path("user") long userId);

    /**
     * Get a user’s shot likes list
     *
     * @param userId   User ID to get list shot likes
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Shots count per one page
     *
     * @return          Network operation result
     */
    @GET("users/{user}/likes")
    Call<List<Like>> getUsersLikes(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * Get the authenticated user’s shot likes list
     *
     * @return          Network operation result
     */
    @GET("user/likes")
    Call<List<Like>> getAuthenticatedUsersLikes();


    /************************************** USER PROJECTS ***************************************************/

    /**
     * Get a user’s projects list
     *
     * @param userId    User ID to get project list
     *
     * @return          Network operation result
     */
    @GET("users/{user}/projects")
    Call<List<Project>> getUsersProjects(@Path("user") long userId);

    /**
     * Get the authenticated user’s projects list
     *
     * @return          Network operation result
     */
    @GET("user/projects")
    Call<List<Project>> getAuthenticatedUsersProjects();


    /************************************** USER SHOTS ******************************************************/

    /**
     * Get a user’s shots list
     *
     * @param userId    User ID to get shot list
     *
     * @return          Network operation result
     */
    @GET("users/{user}/shots")
    Call<List<Shot>> getUsersShots(@Path("user") long userId);

    /**
     * Get a user’s shots list by page
     *
     * @param userId    User ID to get shot list
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Number of shot per page
     *
     * @return         Network operation result
     */
    @GET("users/{user}/shots")
    Call<List<Shot>> getUsersShots(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

    /**
     * Get authenticated user's shots list
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param perPage  Number of shot per page
     *
     * @return         Network operation result
     */
    @GET("user/shots")
    Call<List<Shot>> getAuthenticatedUsersShots(@Query("page") int page, @Query("per_page") int perPage);


    /************************************** USER TEAMS ******************************************************/

    /**
     * Get a user’s teams list
     *
     * @param userId    User ID to get shot list
     *
     * @return          Network operation result
     */
    @GET("users/{user}/teams")
    Call<List<Team>> getUsersTeams(@Path("user") long userId);

    /**
     * Get authenticated user's teams list
     *
     * @param page      Page number, used to receive result partially by pages.
     *                  Increase this value by 1 for each next request
     *
     * @return          Network operation result
     */
    @GET("user/teams")
    Call<List<Team>> getAuthenticatedUsersTeams(@Query("page") int page);
}
