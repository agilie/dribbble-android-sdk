package agilie.dribbblesdk.service.retrofit.services;

import java.util.List;

import agilie.dribbblesdk.domain.Bucket;
import agilie.dribbblesdk.domain.Shot;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by serg on 7/9/15.
 */
public interface DribbbleBucketsService {

    /**
     * Get a bucket
     *
     * @param bucketId  Bucket ID
     * @param callback  Network operation result
     */
    @GET("/buckets/{id}")
    void getBucket(@Path("id")long bucketId, Callback<Bucket> callback);

    /**
     * Create a bucket.
     * Creating a bucket requires the user to be authenticated with the <u>write</u> scope
     *
     * @param bucket    The bucket to create. Field <code>Bucket.name</code> is required
     * @param callback  Network operation result
     */
    @POST("/buckets")
    void createBucket(@Body Bucket bucket, Callback<Bucket> callback);

    /**
     * Update a bucket.
     * Updating a bucket requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param bucketId  Bucket ID to update
     * @param bucket    The bucket to update
     * @param callback  Network operation result
     */
    @PUT("/buckets/{id}")
    void updateBucket(@Path("id") long bucketId, @Body Bucket bucket, Callback<Bucket> callback);

    /**
     * Delete a bucket.
     * Deleting a bucket requires the user to be authenticated with the <u>write</u> scope. The authenticated user must also own the bucket
     *
     * @param bucketId  Bucket ID to delete
     * @param callback  Network operation result
     */
    @DELETE("/buckets/{id}")
    void deleteBucket(@Path("id") long bucketId, Callback<Void> callback);

    /************************************** BUCKET SHOTS ************************************************/

    /**
     * List shots for a bucket
     *
     * @param bucketId  Bucket ID
     * @param callback  Network operation result
     */
    @GET("/buckets/{id}/shots")
    void getShotsForBucket(@Path("id")long bucketId, Callback<List<Shot>> callback);

    /**
     * Add a shot to a bucket.
     * Adding a shot to a bucket requires the user to be authenticated with the <u>write</u> scope.
     * The authenticated user must also own the bucket.S
     *
     * @param shotId        Shot ID to add
     * @param bucketId      Bucket ID
     * @param callback      Network operation result
     */
    @PUT("/buckets/{id}/shots")
    void addShotToBucket(@Path("id")long bucketId, @Query("shot_id") long shotId, Callback<Void> callback);

    /**
     * Remove a shot from a bucket
     * Removing a shot from a bucket requires the user to be authenticated with the <u>write</u> scope.
     * The authenticated user must also own the bucket.
     *
     * @param shotId        Shot ID to remove
     * @param bucketId      Bucket ID
     * @param callback      Network operation result
     */
    @DELETE("/buckets/{id}/shots")
    void removeShotFromBucket(@Path("id")long bucketId, @Query("shot_id") long shotId, Callback<Void> callback);
}
