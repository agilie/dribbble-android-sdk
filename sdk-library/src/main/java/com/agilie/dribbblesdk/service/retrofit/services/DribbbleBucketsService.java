package com.agilie.dribbblesdk.service.retrofit.services;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Shot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by serg on 7/9/15.
 */
public interface DribbbleBucketsService {

    /**
     * Get a bucket
     *
     * @param bucketId  Bucket ID
     *
     * @return          Network operation result
     */
    @GET("buckets/{id}")
    Call<Bucket> getBucket(@Path("id") long bucketId);

    /**
     * Create a bucket.
     * Creating a bucket requires the user to be authenticated with the <u>write</u> scope
     *
     * @param bucket    The bucket to create. Field <code>Bucket.name</code> is required
     *
     * @return          Network operation result
     */
    @POST("buckets")
    Call<Bucket> createBucket(@Body Bucket bucket);

    /**
     * Update a bucket.
     * Updating a bucket requires the user to be authenticated with the <u>write</u> scope.
     *
     * @param bucketId  Bucket ID to update
     * @param bucket    The bucket to update
     *
     * @return          Network operation result
     */
    @PUT("buckets/{id}")
    Call<Bucket> updateBucket(@Path("id") long bucketId, @Body Bucket bucket);

    /**
     * Delete a bucket.
     * Deleting a bucket requires the user to be authenticated with the <u>write</u> scope. The authenticated user must also own the bucket
     *
     * @param bucketId  Bucket ID to delete
     *
     * @return          Network operation result
     */
    @DELETE("buckets/{id}")
    Call<Void> deleteBucket(@Path("id") long bucketId);

    /************************************** BUCKET SHOTS ************************************************/

    /**
     * List shots for a bucket
     *
     * @param bucketId  Bucket ID
     *
     * @return          Network operation result
     */
    @GET("buckets/{id}/shots")
    Call<List<Shot>> getShotsForBucket(@Path("id") long bucketId);

    /**
     * Add a shot to a bucket.
     * Adding a shot to a bucket requires the user to be authenticated with the <u>write</u> scope.
     * The authenticated user must also own the bucket.S
     *
     * @param shotId        Shot ID to add
     * @param bucketId      Bucket ID
     *
     * @return              Network operation result
     */
    @PUT("buckets/{id}/shots")
    Call<Void> addShotToBucket(@Path("id") long bucketId, @Query("shot_id") long shotId);

    /**
     * Remove a shot from a bucket
     * Removing a shot from a bucket requires the user to be authenticated with the <u>write</u> scope.
     * The authenticated user must also own the bucket.
     *
     * @param shotId        Shot ID to remove
     * @param bucketId      Bucket ID
     *
     * @return              Network operation result
     */
    @DELETE("buckets/{id}/shots")
    Call<Void> removeShotFromBucket(@Path("id") long bucketId, @Query("shot_id") long shotId);
}
