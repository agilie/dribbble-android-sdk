package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleBucketsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DribbbleBucketsServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    private static final long TEST_BUCKET_ID = 267166;

    private static final long TEST_AUTHORIZED_USER_BUCKET_ID = 274205;
    private static final long TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET = 1999287;
    private static final long TEST_SHOT_ID = 1997749;

    private DribbbleBucketsService authorizedDribbbleService;
    private DribbbleBucketsService dribbbleService;

    public DribbbleBucketsServiceTest() {
        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(AUTH_TOKEN_FOR_TEST);
        Retrofit retrofit = DribbbleWebServiceHelper.getRetrofitBuilder(okHttpClientBuilder).build();
        authorizedDribbbleService = DribbbleWebServiceHelper.getDribbbleBucketService(retrofit);
        dribbbleService = DribbbleWebServiceHelper.getDribbbleBucketService(retrofit);
    }

    public void testGetBucket() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getBucket(TEST_BUCKET_ID)
                        .enqueue(new Callback<Bucket>() {
                            @Override
                            public void onResponse(Call<Bucket> call, Response<Bucket> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<Bucket> call, Throwable t) {
                                assertTrue("testGetBucket is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testCreateDeleteBucket() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                Bucket bucket = new Bucket();
                bucket.setName("BucketName");
                bucket.setDescription("Description for this bucket");
                authorizedDribbbleService.createBucket(bucket)
                        .enqueue(new Callback<Bucket>() {
                            @Override
                            public void onResponse(Call<Bucket> call, Response<Bucket> response) {
                                assertNotNull(response.body());
                                authorizedDribbbleService.deleteBucket(response.body().getId()).enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        assertTrue("testDeleteBucket is failed", false);
                                        signal.countDown();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<Bucket> call, Throwable t) {
                                assertTrue("testCreateBucket is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testCreateUpdateDeleteBucket() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                Bucket bucket = new Bucket();
                bucket.setName("BucketName");
                bucket.setDescription("Description for this bucket");
                authorizedDribbbleService.createBucket(bucket)
                        .enqueue(new Callback<Bucket>() {
                            @Override
                            public void onResponse(Call<Bucket> call, Response<Bucket> response) {
                                Bucket bucket = response.body();
                                assertNotNull(bucket);
                                bucket.setName("New BucketName");
                                authorizedDribbbleService.updateBucket(bucket.getId(), bucket)
                                        .enqueue(new Callback<Bucket>() {
                                            @Override
                                            public void onResponse(Call<Bucket> call, Response<Bucket> response) {
                                                Bucket bucket = response.body();
                                                assertNotNull(bucket);
                                                authorizedDribbbleService.deleteBucket(bucket.getId())
                                                        .enqueue(new Callback<Void>() {

                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                signal.countDown();
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {
                                                                assertTrue("testDeleteBucket is failed", false);
                                                                signal.countDown();
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onFailure(Call<Bucket> call, Throwable t) {
                                                assertTrue("testUpdateBucket is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<Bucket> call, Throwable t) {
                                assertTrue("testCreateBucket is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetShotsForBucket() throws Throwable {

        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getShotsForBucket(TEST_BUCKET_ID)
                        .enqueue(new Callback<List<Shot>>() {
                                     @Override
                                     public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                         assertNotNull(response.body());
                                         signal.countDown();
                                     }

                                     @Override
                                     public void onFailure(Call<List<Shot>> call, Throwable t) {
                                         assertTrue("testGetShotsForBucket is failed", false);
                                         signal.countDown();
                                     }
                                 }
                        );
            }
        });

        signal.await();
    }

    public void testAddRemoveBucketShot() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                final Shot shot = new Shot();
                shot.setId(TEST_SHOT_ID);
                authorizedDribbbleService.addShotToBucket(TEST_AUTHORIZED_USER_BUCKET_ID, TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET)
                        .enqueue(new Callback<Void>() {
                                     @Override
                                     public void onResponse(Call<Void> call, Response<Void> response) {
                                         authorizedDribbbleService.removeShotFromBucket(TEST_AUTHORIZED_USER_BUCKET_ID, TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET).enqueue(
                                                 new Callback<Void>() {
                                                     @Override
                                                     public void onResponse(Call<Void> call, Response<Void> response) {
                                                         signal.countDown();
                                                     }

                                                     @Override
                                                     public void onFailure(Call<Void> call, Throwable t) {
                                                         assertTrue("testRemoveShotFromBucket is failed", false);
                                                         signal.countDown();
                                                     }
                                                 }
                                         );
                                     }

                                     @Override
                                     public void onFailure(Call<Void> call, Throwable t) {

                                     }
                                 }
                        );
            }
        });
        signal.await();
    }

}
