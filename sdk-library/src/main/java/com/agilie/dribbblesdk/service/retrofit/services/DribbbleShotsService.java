package com.agilie.dribbblesdk.service.retrofit.services;

import com.agilie.dribbblesdk.domain.Attachment;
import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Comment;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Rebound;
import com.agilie.dribbblesdk.domain.Shot;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by serg on 7/9/15.
 */
public interface DribbbleShotsService {

    /**
     * Get shots list (popular shots by default)
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     *
     * @return         Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("page") int page);

    /**
     * Get shots list (popular shots by default)
     *
     * @param page     Page number, used to receive result partially by pages.
     *                 Increase this value by 1 for each next request
     *
     * @return         Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage);

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
     *
     * @return         Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort);

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
     *
     * @return          Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe);

    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("page") int page, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe);

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
     *
     * @return         Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@Query("list") String list);

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
     *
     * @return               Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchShots(@QueryMap Map<String, Object> parameters);

    /**
     * Get shots list
     *
     * @param sort     Allowed values:
     *                 <code>Shot.SORT_COMMENTS</code>
     *                 <code>Shot.SORT_RECENT</code>
     *                 <code>Shot.SORT_VIEWS</code>
     *
     * @return         Network operation result
     */
    @GET("shots")
    Call<List<Shot>> fetchSortedShots(@Query("sort") String sort);

    /**
     * Get a shot
     *
     * @param shotId   Shot ID to receive
     * @return         Network operation result
     */
    @GET("shots/{id}")
    Call<Shot> getShot(@Path("id") long shotId);

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
     *
     * @return                  Network operation result
     */
    @Multipart
    @POST("shots")
    Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image, @Part("description") String description,
                          @Part("tags") String[] tags, @Part("team_id") int teamId, @Part("rebound_source_id") int reboundSourceId);

    @Multipart
    @POST("shots")
    Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image, @Part("description") String description,
                          @Part("tags") String[] tags);

    @Multipart
    @POST("shots")
    Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image);


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
     * @return          Network operation result
     */
    @Multipart
    @POST("shots")
    Call<Void> createShot(@PartMap Map<String, Object> partMap);

    /**
     * Update a shot.
     *
     * Updating a shot requires the user to be authenticated with the upload scope.
     * The authenticated user must also own the shot.
     *
     * @param shotId   Shot ID to update
     *
     * @return         Network operation result
     */
    @Multipart
    @PUT("shots/{id}")
    Call<Shot> updateShot(@Path("id") long shotId, @Part("title") String title, @Part("description") String description,
                          @Part("team_id") int teamId, @Part("tags") String[] tags);

    @Multipart
    @PUT("shots/{id}")
    Call<Shot> updateShot(@Path("id") long shotId, @Part("title") String title, @Part("description") String description,
                          @Part("tags") String[] tags);

    @Multipart
    @PUT("shots/{id}")
    Call<Shot> updateShot(@Path("id") long shotId, @Part("description") String description);
    /**
     * Delete a shot.
     *
     * Deleting a shot requires the user to be authenticated with the upload scope.
     * The authenticated user must also own the shot.
     *
     * @param shotId   Shot ID to delete.
     *
     * @return         Network operation result
     */
    @DELETE("shots/{id}")
    Call<Void> deleteShot(@Path("id") long shotId);


    /************************************** SHOT ATTACHMENTS ************************************************/

    /**
     * List the attachments for a shot
     *
     * @param shotId    Shot ID
     *
     * @return          Network operation result
     */
    @GET("shots/{id}/attachments")
    Call<List<Attachment>> getShotAttachments(@Path("id") long shotId);

    /**
     * Create a shot attachment.
     *
     * Creating an attachment requires the user to be authenticated with the <u>upload</u> scope.
     * The authenticated user must own the shot and be a pro, a team, or a member of a team.
     *
     * @param shotId    Shot ID to create attachment for
     * @param file      Attachment to be created. <b>Required.</b> The attachment file.
     *                  It must be no larger than 10 megabytes.
     *
     * @return          Network operation result
     */
    @POST("shots/{shot}/attachments")
    Call<Void> createShotAttachment(@Path("shot") long shotId, @Part("file") RequestBody file);

    /**
     * Get a single attachment
     *
     * A <u>thumbnail_url</u> is only present for image attachments.
     *
     * @param shotId        Shot ID to get attachment for
     * @param attachmentId  Attachment ID to receive
     *
     * @return              Network operation result
     */
    @GET("shots/{shot}/attachments/{id}")
    Call<Attachment> getShotAttachment(@Path("shot") long shotId, @Path("id") long attachmentId);

    /**
     * Delete an attachment
     *
     * Deleting an attachment requires the user to be authenticated with the <u>upload</u> scope.
     * The authenticated user must also own the attachment.
     *
     * @param shotId        Shot ID to delete attachment for
     * @param attachmentId  Attachment ID to delete
     *
     * @return              Network operation result
     */
    @DELETE("shots/{shot}/attachments/{id}")
    Call<Void> deleteShotAttachment(@Path("shot") long shotId, @Path("id") long attachmentId);


    /************************************** SHOT BUCKETS ****************************************************/

    /**
     * List buckets for a shot
     *
     * @param shotId    Shot ID with the list of buckets
     *
     * @return          Network operation result
     */
    @GET("shots/{id}/buckets")
    Call<List<Bucket>> getShotBuckets(@Path("id") long shotId);


    /************************************** SHOT COMMENTS ***************************************************/

    /**
     * List of comments for a shot
     *
     * @param shotId   Shot ID with the list of comments
     *
     * @return         Network operation result
     */

    @GET("shots/{shot}/comments")
    Call<List<Comment>> getShotComments(@Path("shot") long shotId);


    /**
     * List of likes for a comment
     *
     * @param shotId    Shot ID with the given comment
     * @param commentId Comment ID with the list of likes
     *
     * @return          Network operation result
     */

    @GET("shots/{shot}/comments/{id}/likes")
    Call<List<Like>> getCommentLikes(@Path("shot") long shotId, @Path("id") long commentId);


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
     *
     * @return          Network operation result
     */

    @POST("shots/{shot}/comments")
    Call<Comment> createComment(@Path("shot") long shotId, @Body Comment body);

    /**
     * Get a single comment
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to return
     *
     * @return          Network operation result
     */

    @GET("shots/{shot}/comments/{id}")
    Call<Comment> getShotComment(@Path("shot") long shotId, @Path("id") long commentId);

    /**
     * Update a comment
     *
     * Updating a comment requires the user to be authenticated with the <u>comment</u> scope.
     * The authenticated user must also own the comment.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to update
     * @param comment   Comment content
     *
     * @return          Network operation result
     */

    @PUT("shots/{shot}/comments/{id}")
    Call<Comment> updateShotComment(@Path("shot") long shotId, @Path("id") long commentId, @Body Comment comment);

    /**
     * Delete a comment
     *
     * Deleting a comment requires the user to be authenticated with the <u>comment</u> scope.
     * The authenticated user must also own the comment.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to remove
     *
     * @return          Network operation result
     */

    @DELETE("shots/{shot}/comments/{id}")
    Call<Void> deleteShotComment(@Path("shot") long shotId, @Path("id") long commentId);

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
     *
     * @return          Network operation result
     */

    @GET("shots/{shot}/comments/{id}/like")
    Call<Like> checkIsLikedShotComment(@Path("shot") long shotId, @Path("id") long commentId);

    /**
     * Like a comment
     *
     * Liking a comment requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to like comment
     * @param commentId Comment ID to like
     *
     * @return          Network operation result
     */

    @POST("shots/{shot}/comments/{id}/like")
    Call<Like> likeShotComment(@Path("shot") long shotId, @Path("id") long commentId);

    /**
     * Unlike a comment
     *
     * Unliking a comment requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID
     * @param commentId Comment ID to unlike
     *
     * @return          Network operation result
     */

    @DELETE("shots/{shot}/comments/{id}/like")
    Call<Void> unlikeShotComment(@Path("shot") long shotId, @Path("id") long commentId);


    /************************************** SHOT LIKES ******************************************************/
    /********************************************************************************************************/

    /**
     * Get list of likes for a shot
     *
     * @param shotId   Shot ID with the list of likes
     *
     * @return         Network operation result
     */

    @GET("shots/{id}/likes")
    Call<List<Like>> getShotLikes(@Path("id") long shotId);

    /**
     * Check if you like a shot.
     * Checking for a shot like requires the user to be authenticated.
     *
     * Note that returned result may contain "404 not found" error, if the user does not like the shot.
     * It's better use synchronous method which returns Response.class.
     *
     * @param shotId   Shot ID to check
     *
     * @return         Network operation result
     */
    @GET("shots/{id}/like")
    Call<Like> checkShotIsLiked(@Path("id") long shotId);

    /**
     * Like a shot
     *
     * Liking a shot requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to like
     *
     * @return          Network operation result
     */

    @POST("shots/{id}/like")
    Call<Like> likeShot(@Path("id") long shotId);

    /**
     * Unlike a shot
     *
     * Unliking a shot requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param shotId    Shot ID to unlike
     *
     * @return          Network operation result
     */

    @DELETE("shots/{id}/like")
    Call<Void> unlikeShot(@Path("id") long shotId);


    /************************************** SHOT PROJECTS ***************************************************/

    /**
     * List projects for a shot
     *
     * @param shotId   Shot ID with the list of projects
     *
     * @return         Network operation result
     */

    @GET("shots/{id}/projects")
    Call<List<Project>> getShotProjectsList(@Path("id") long shotId);


    /************************************** SHOT REBOUNDS ***************************************************/

    /**
     * List rebounds for a shot
     *
     * @param shotId   Shot ID with the list of rebounds
     *
     * @return         Network operation result
     */

    @GET("shots/{id}/rebounds")
    Call<List<Rebound>> getShotReboundsList(@Path("id") long shotId);
}
