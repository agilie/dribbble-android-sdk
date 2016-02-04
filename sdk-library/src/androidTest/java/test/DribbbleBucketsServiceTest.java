package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleServiceGenerator;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleBucketsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DribbbleBucketsServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    private static final long TEST_BUCKET_ID = 267166;

    private static final long TEST_AUTHORIZED_USER_BUCKET_ID = 274205;
    private static final long TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET = 1999287;
    private static final long TEST_SHOT_ID = 1997749;

    private DribbbleBucketsService authorizedDribbbleService;
    private DribbbleBucketsService dribbbleService;

    public DribbbleBucketsServiceTest() {
        authorizedDribbbleService = DribbbleServiceGenerator.getDribbbleBucketService(AUTH_TOKEN_FOR_TEST);
        dribbbleService = DribbbleServiceGenerator.getDribbbleBucketService(AUTH_TOKEN_FOR_TEST);
    }

    public void testGetBucket() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getBucket(TEST_BUCKET_ID, new Callback<Bucket>() {
                    @Override
                    public void success(final Bucket bucket, Response response) {
                        assertNotNull(bucket);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                authorizedDribbbleService.createBucket(bucket, new Callback<Bucket>() {
                    @Override
                    public void success(final Bucket bucket, Response response) {
                        assertNotNull(bucket);
                        authorizedDribbbleService.deleteBucket(bucket.getId(), new Callback<Void>() {
                            @Override
                            public void success(Void aVoid, Response response) {
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("testDeleteBucket is failed", false);
                                signal.countDown();
                            }
                        });

                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                authorizedDribbbleService.createBucket(bucket, new Callback<Bucket>() {
                    @Override
                    public void success(final Bucket bucket, Response response) {
                        assertNotNull(bucket);
                        bucket.setName("New BucketName");
                        authorizedDribbbleService.updateBucket(bucket.getId(), bucket, new Callback<Bucket>() {
                            @Override
                            public void success(Bucket bucket, Response response) {
                                assertNotNull(bucket);
                                authorizedDribbbleService.deleteBucket(bucket.getId(), new Callback<Void>() {
                                    @Override
                                    public void success(Void aVoid, Response response) {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        assertTrue("testDeleteBucket is failed", false);
                                        signal.countDown();
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("testUpdateBucket is failed", false);
                                signal.countDown();
                            }
                        });

                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotsForBucket(TEST_BUCKET_ID, new Callback<List<Shot>>() {
                    @Override
                    public void success(final List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("testGetShotsForBucket is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testAddRemoveBucketShot() throws  Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                final Shot shot = new Shot();
                shot.setId(TEST_SHOT_ID);
                authorizedDribbbleService.addShotToBucket(TEST_AUTHORIZED_USER_BUCKET_ID, TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET, new Callback<Void>() {
                    @Override
                    public void success(final Void aVoid, Response response) {
                        authorizedDribbbleService.removeShotFromBucket(TEST_AUTHORIZED_USER_BUCKET_ID, TEST_SHOT_TO_ADD_REMOVE_FROM_BUCKET, new Callback<Void>() {
                            @Override
                            public void success(Void aVoid, Response response) {
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("testRemoveShotFromBucket is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("testAddAhotToBucket is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

}
