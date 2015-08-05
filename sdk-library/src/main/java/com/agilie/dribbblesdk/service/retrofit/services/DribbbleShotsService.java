package com.agilie.dribbblesdk.service.retrofit.services;

import java.util.List;
import java.util.Map;

import com.agilie.dribbblesdk.domain.Attachment;
import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Comment;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Rebound;
import com.agilie.dribbblesdk.domain.Shot;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;

public interface DribbbleShotsService {

    /**
     * Get shots list (popular shots by default)
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchShots(@Query("page") int page, Callback<List<Shot>> callback);

    /**
     * Get shots list (popular shots by default)
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchShots(@Query("page") int page, @Query("per_page") int perPage, Callback<List<Shot>> callback);

    /**
     * Get shots list
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     * @param list     Allowed values:
     *                 <br/><code>Shot.LIST_ANIMATED</code>
     *                 <br/><code>Shot.LIST_ATTACHMENTS</code>
     *                 <br/><code>Shot.LIST_DEBUTS</code>
     *                 <br/><code>Shot.LIST_PLAYOFFS</code>
     *                 <br/><code>Shot.LIST_REBOUNDS</code>
     *                 <br/><code>Shot.LIST_TEAMS</code>
     *                 <br/>Default: Results of any type.
     * @param sort     Allowed values:
     *                 <br/><code>Shot.SORT_COMMENTS</code>
     *                 <br/><code>Shot.SORT_RECENT</code>
     *                 <br/><code>Shot.SORT_VIEWS</code>
     *                 <br/>Default: Results are sorted by popularity.
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort, Callback<List<Shot>> callback);

    /**
     * Get shots list
     *
     * @param page      Page number, used to receive result partially by pages. Increase this value by 1 for each next request
     * @param list      Allowed values:
     *                  <code>Shot.LIST_ANIMATED</code>
     *                  <code>Shot.LIST_ATTACHMENTS</code>
     *                  <code>Shot.LIST_DEBUTS</code>
     *                  <code>Shot.LIST_PLAYOFFS</code>
     *                  <code>Shot.LIST_REBOUNDS</code>
     *                  <code>Shot.LIST_TEAMS</code>
     *                  Default: Results of any type.
     * @param sort      Allowed values:
     *                  <code>Shot.SORT_COMMENTS</code>
     *                  <code>Shot.SORT_RECENT</code>
     *                  <code>Shot.SORT_VIEWS</code>
     *                  Default: Results are sorted by popularity.
     * @param date      Limit the timeframe to a specific date, week, month, or year.
     *                  Format: of YYYY-MM-DD.
     * @param timeframe A period of time to limit the results.
     *                  Allowed values:
     *                  <code>Shot.TIMEFRAME_WEEKS</code>
     *                  <code>Shot.TIMEFRAME_MONTH</code>
     *                  <code>Shot.TIMEFRAME_YEAR</code>
     *                  <code>Shot.TIMEFRAME_EVER</code>
     *                  <p/>
     *                  Note that the value is ignored when sorting with recent.
     *                  Default: Results from now.
     * @param callback  Network operation result
     */
    @GET("/shots")
    void fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe, Callback<List<Shot>> callback);

    @GET("/shots")
    void fetchShots(@Query("page") int page, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe, Callback<List<Shot>> callback);

    /**
     * Get shots list
     *
     * @param list     Allowed values:
     *                 <code>Shot.LIST_ANIMATED</code>
     *                 <code>Shot.LIST_ATTACHMENTS</code>
     *                 <code>Shot.LIST_DEBUTS</code>
     *                 <code>Shot.LIST_PLAYOFFS</code>
     *                 <code>Shot.LIST_REBOUNDS</code>
     *                 <code>Shot.LIST_TEAMS</code>
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchShots(@Query("list") String list, Callback<List<Shot>> callback);

    /**
     * Get shots list
     *
     * @param parameters     Allowed parameters:
     *                       <b>list</b>
     *                                        <code>Shot.LIST_ANIMATED</code>
     *                                        <code>Shot.LIST_ATTACHMENTS</code>
     *                                        <code>Shot.LIST_DEBUTS</code>
     *                                        <code>Shot.LIST_PLAYOFFS</code>
     *                                        <code>Shot.LIST_REBOUNDS</code>
     *                                        <code>Shot.LIST_TEAMS</code>
     *                                        Default: Results of any type.
     *                       <b>sort</b>
     *                                        <code>Shot.SORT_COMMENTS</code>
     *                                        <code>Shot.SORT_RECENT</code>
     *                                        <code>Shot.SORT_VIEWS</code>
     *                       <b>timeframe</b> A period of time to limit the results.
     *                                        Allowed values:
     *                                        <code>Shot.TIMEFRAME_WEEKS</code>
     *                                        <code>Shot.TIMEFRAME_MONTH</code>
     *                                        <code>Shot.TIMEFRAME_YEAR</code>
     *                                        <code>Shot.TIMEFRAME_EVER</code>
     *                                        <p/>
     *                                        Note that the value is ignored when sorting with recent.
     *                                        Default: Results from now.
     *                       <b>date</b>      Limit the timeframe to a specific date, week, month, or year.
     *                                        Format: YYYY-MM-DD.
     *                       <b>page</b>      to receive result partially by pages.
     *                                        Increase this value by 1 for each next request
     *                       <b>per_page</b>  Number of items per page.
     *
     *                       Parameters name - the type of keys maintained by this map
     *                       Parameters value - the type of mapped values
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchShots(@QueryMap Map<String, Object> parameters, Callback<List<Shot>> callback);

    /**
     * Get shots list
     *
     * @param sort     Allowed values:
     *                 <code>Shot.SORT_COMMENTS</code>
     *                 <code>Shot.SORT_RECENT</code>
     *                 <code>Shot.SORT_VIEWS</code>
     * @param callback Network operation result
     */
    @GET("/shots")
    void fetchSortedShots(@Query("sort") String sort, Callback<List<Shot>> callback);

    /**
     * Get a shot
     *
     * @param shotId   Shot ID to receive
     * @param callback Network operation result
     */
    @GET("/shots/{id}")
    void getShot(@Path("id") long shotId, Callback<Shot> callback);

    /**
     * Create a shot.
     *
     * Creating a shot happens asynchronously. After creation the returned location will
     * return a 404 Not Found until processing is completed.
     * If this takes longer than five minutes, be sure to contact support.
     *
     * @param title             <b>Required.</b> The title of the shot.
     * @param image             <b>Required.</b> The image file.
     *                          It must be exactly 400x300 or 800x600, no larger
     *                          than eight megabytes, and be a GIF, JPG, or PNG.
     * @param description       A description of the shot.
     * @param tags              Tags for the shot.
     *                          Limited to a maximum of 12 tags.
     * @param teamId            An ID of a team to associate the shot with.
     *                          The authenticated user must either be a member
     *                          of the team or be authenticated as the same team.
     * @param reboundSourceId   An ID of a shot that the new shot is a rebound of.
     *                          The shot must be reboundable and by a user not blocking
     *                          the authenticated user.
     * @param callback          Network operation result
     */
    @Multipart
    @POST("/shots")
    void createShot(@Part("title") String title, @Part("image") TypedFile image, @Part("description") String description,
                    @Part("tags") String[] tags, @Part("team_id") int teamId, @Part("rebound_source_id") int reboundSourceId, Callback<Void> callback);

    @Multipart
    @POST("/shots")
    void createShot(@Part("title") String title, @Part("image") TypedFile image, @Part("description") String description,
                    @Part("tags") String[] tags, Callback<Void> callback);

    @Multipart
    @POST("/shots")
    void createShot(@Part("title") String title, @Part("image") TypedFile image, Callback<Void> callback);


    /**
     * Create a shot by using PartMap
     *
     * Creating a shot happens asynchronously. After creation the returned location will
     * return a 404 Not Found until processing is completed.
     * If this takes longer than five minutes, be sure to contact support.
     *
     * @param partMap   allowable put parameters into Map&ltString, Object&gt such as:
     *                  <b>title</b>                <b>Required.</b> The title of the shot.
     *                  <b>image</b>                <b>Required.</b> The image file.
     *                                              It must be exactly 400x300 or 800x600,
     *                                              no larger than eight megabytes, and be a GIF, JPG, or PNG.
     *                  <b>description</b>          A description of the shot.
     *                  <b>tags</b>                 Tags for the shot.
     *                                              Limited to a maximum of 12 tags.
     *                  <b>team_id</b>              An ID of a team to associate the shot with.
     *                                              The authenticated user must either be a member
     *                                              of the team or be authenticated as the same team.
     *                  <b>rebound_source_id</b>    An ID of a shot that the new shot is a rebound of.
     *                                              The shot must be reboundable and by a user
     *                                              not blocking the authenticated user.
     *
     *                  Parameters name - the type of keys maintained by this map
     *                  Parameters value - the type of mapped values
     *
     * @param callback  network operation result
     */
    @Multipart
    @POST("/shots")
    void createShot(@PartMap Map<String, Object> partMap, Callback<Void> callback);

    /**
     * Update a shot.
     *
     * Updating a shot requires the user to be authenticated with the upload scope.
     * The authenticated user must also own the shot.
     *
     * @param shotId   Shot ID to update
     * @param callback Network operation result
     */
    @Multipart
    @PUT("/shots/{id}")
    void updateShot(@Path("id") long shotId, @Part("title") String title, @Part("description") String description,
                    @Part("team_id") int teamId, @Part("tags") String[] tags, Callback<Shot> callback);

    @Multipart
    @PUT("/shots/{id}")
    void updateShot(@Path("id") long shotId, @Part("title") String title, @Part("description") String description,
                    @Part("tags") String[] tags, Callback<Shot> callback);

    @Multipart
    @PUT("/shots/{id}")
    void updateShot(@Path("id") long shotId, @Part("description") String description, Callback<Shot> callback);
    /**
     * Delete a shot.
     *
     * Deleting a shot requires the user to be authenticated with the upload scope.
     * The authenticated user must also own the shot.
     *
     * @param shotId   Shot ID to delete.
     * @param callback Network operation result
     */
    @DELETE("/shots/{id}")
    void deleteShot(@Path("id") long shotId, Callback<Void> callback);


    /************************************** SHOT ATTACHMENTS ************************************************/

    /**
     * List the attachments for a shot
     *
     * @param shotId    Shot ID
     * @param callback  Network operation result
     */
    @GET("/shots/{id}/attachments")
    void getShotAttachments(@Path("id")long shotId, Callback<List<Attachment>> callback);

    /**
     * Create a shot attachment.
     *
     * Creating an attachment requires the user to be authenticated with the <u>upload</u> scope.
     * The authenticated user must own the shot and be a pro, a team, or a member of a team.
     *
     * @param shotId    Shot ID to create attachment for
     * @param file      Attachment to be created. <b>Required.</b> The attachment file.
     *                  It must be no larger than 10 megabytes.
     * @param callback  Network operation result
     */
    @POST("/shots/{shot}/attachments")
    void createShotAttachment(@Path("shot")long shotId, @Part("file") TypedFile file, Callback<Void> callback);

    /**
     * Get a single attachment
     *
     * A <u>thumbnail_url</u> is only present for image attachments.
     *
     * @param shotId        Shot ID to get attachment for
     * @param attachmentId  Attachment ID to receive
     * @param callback      Network operation result
     */
    @GET("/shots/{shot}/attachments/{id}")
    void getShotAttachment(@Path("shot")long shotId, @Path("id")long attachmentId, Callback<Attachment> callback);

    /**
     * Delete an attachment
     *
     * Deleting an attachment requires the user to be authenticated with the <u>upload</u> scope.
     * The authenticated user must also own the attachment.
     *
     * @param shotId        Shot ID to delete attachment for
     * @param attachmentId  Attachment ID to delete
     * @param callback      Network operation result
     *
     */
    @DELETE("/shots/{shot}/attachments/{id}")
    void deleteShotAttachment(@Path("shot")long shotId, @Path("id")long attachmentId, Callback<Void> callback);


    /************************************** SHOT BUCKETS ****************************************************/

    /**
     * List buckets for a shot
     *
     * @param shotId    Shot ID with the list of buckets
     * @param callback  Network operation result
     */
    @GET("/shots/{id}/buckets")
    void getShotBuckets(@Path("id")long shotId, Callback<List<Bucket>> callback);


    /************************************** SHOT COMMENTS ***************************************************/

    /**
     * List of comments for a shot
     *
     * @param shotId   Shot ID with the list of comments
     * @param callback Network operation result
     */

    @GET("/shots/{shot}/comments")
    void getShotComments(@Path("shot") long shotId, Callback<List<Comment>> callback);


    /**
     * List of likes for a comment
     *
     * @param shotId    Shot ID with the given comment
     * @param commentId Comment ID with the list of likes
     * @param callback  Network operation result
     */

    @GET("/shots/{shot}/comments/{id}/likes")
    void getCommentLikes(@Path("shot") long shotId, @Path("id") long commentId, Callback<List<Like>> callback);


    /**
     * Create a comment
     *
     * Creating a comment requires the user to be authenticated with the <u>comment</u> scope.
     * The authenticated user must also be a player or team.
     *
     * Any username mentions, such as @simplebits, are automatically parsed and linked.
     *
     * @param shotId    Shot ID
     * @param body      Content to update
     * @param callback  Network operation result
     */

    @POST("/shots/{shot}/comments")
    void createComment(@Path("shot") long shotId, @Body Comment body, Callback<Comment> callback);

    /**
     * Get a single comment
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to return
     * @param callback  Network operation result
     */

    @GET("/shots/{shot}/comments/{id}")
    void getShotComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Comment> callback);

    /**
     * Update a comment
     *
     * Updating a comment requires the user to be authenticated with the <u>comment</u> scope.
     * The authenticated user must also own the comment.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to update
     * @param comment   Comment content
     * @param callback  Network operation result
     */

    @PUT("/shots/{shot}/comments/{id}")
    void updateShotComment(@Path("shot") long shotId, @Path("id") long commentId, @Body Comment comment, Callback<Comment> callback);

    /**
     * Delete a comment
     *
     * Deleting a comment requires the user to be authenticated with the <u>comment</u> scope.
     * The authenticated user must also own the comment.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to remove
     * @param callback  Network operation result
     */

    @DELETE("/shots/{shot}/comments/{id}")
    void deleteShotComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Void> callback);

    /**
     * Check if you like a comment
     *
     * Checking for a comment like requires the user to be authenticated.
     *
     * Note that returned result may contain "404 not found" error, if the user does not like the comment.
     * It's better use synchronous method which returns Response.class.
     *
     * @param shotId    Shot ID to check if the user likes comment
     * @param commentId Comment ID to check if the user likes it
     * @param callback  Network operation result
     */

    @GET("/shots/{shot}/comments/{id}/like")
    void checkIsLikedShotComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Like> callback);

    @GET("/shots/{shot}/comments/{id}/like")
    Response checkIsLikedShotComment(@Path("shot") long shotId, @Path("id") long commentId);

    /**
     * Like a comment
     *
     * Liking a comment requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to like comment
     * @param commentId Comment ID to like
     * @param callback  Network operation result
     */

    @POST("/shots/{shot}/comments/{id}/like")
    void likeShotComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Like> callback);

    /**
     * Unlike a comment
     *
     * Unliking a comment requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to unlike
     * @param callback  Network operation result
     */

    @DELETE("/shots/{shot}/comments/{id}/like")
    void unlikeShotComment(@Path("shot") long shotId, @Path("id") long commentId, Callback<Void> callback);


    /************************************** SHOT LIKES ******************************************************/
    /********************************************************************************************************/

    /**
     * Get list of likes for a shot
     *
     * @param shotId   Shot ID with the list of likes
     * @param callback Network operation result
     */

    @GET("/shots/{id}/likes")
    void getShotLikes(@Path("id") long shotId, Callback<List<Like>> callback);

    /**
     * Check if you like a shot.
     * Checking for a shot like requires the user to be authenticated.
     *
     * Note that returned result may contain "404 not found" error, if the user does not like the shot.
     * It's better use synchronous method which returns Response.class.
     *
     * @param shotId   Shot ID to check
     * @param callback Network operation result
     */
    @GET("/shots/{id}/like")
    void checkShotIsLiked(@Path("id") long shotId, Callback<Like> callback);

    @GET("/shots/{id}/like")
    Response checkShotIsLiked(@Path("id") long shotId);

    /**
     * Like a shot
     *
     * Liking a shot requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to like
     * @param callback  Network operation result
     */

    @POST("/shots/{id}/like")
    void likeShot(@Path("id") long shotId, Callback<Like> callback);

    @POST("/shots/{id}/like")
    Response likeShot(@Path("id") long shotId);

    /**
     * Unlike a shot
     *
     * Unliking a shot requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to unlike
     * @param callback  Network operation result
     */

    @DELETE("/shots/{id}/like")
    void unlikeShot(@Path("id") long shotId, Callback<Void> callback);


    /************************************** SHOT PROJECTS ***************************************************/

    /**
     * List projects for a shot
     *
     * @param shotId   Shot ID with the list of projects
     * @param callback Network operation result
     */

    @GET("/shots/{id}/projects")
    void getShotProjectsList(@Path("id") long shotId, Callback<List<Project>> callback);


    /************************************** SHOT REBOUNDS ***************************************************/

    /**
     * List rebounds for a shot
     *
     * @param shotId   Shot ID with the list of rebounds
     * @param callback Network operation result
     */

    @GET("/shots/{id}/rebounds")
    void getShotReboundsList(@Path("id") long shotId, Callback<List<Rebound>> callback);
}
