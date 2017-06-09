package test;

import android.os.Environment;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.agilie.dribbblesdk.domain.Attachment;
import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Comment;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Rebound;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DribbbleShotsServiceTest extends InstrumentationTestCase {

    protected static final String AUTH_TOKEN_FOR_TEST = "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2";
    // token with all scopes "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2"
    // 3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2
    protected static final long TEST_SHOT_ID = 472178;
    protected static final long TEST_SHOT_WITH_ATTACHMENT_ID = 1999342;
    protected static final long TEST_ATTACHMENT_ID = 351491; // for shot 1999342

    protected static final long TEST_COMMENT_ID = 1146540; // refer to TEST_SHOT_ID

    protected static final String DATE_PATTERN = "yyyy-MM-dd";
    protected DribbbleShotsService authorizedDribbbleService;
    protected DribbbleShotsService dribbbleService;
    protected DribbbleUserService dribbbleUserService;
    protected SimpleDateFormat simpleDateFormat;

    public DribbbleShotsServiceTest() {
        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(AUTH_TOKEN_FOR_TEST);
        Retrofit retrofit = DribbbleWebServiceHelper.getRetrofitBuilder(okHttpClientBuilder).build();

        authorizedDribbbleService = DribbbleWebServiceHelper.getDribbbleShotService(retrofit);
        dribbbleService = DribbbleWebServiceHelper.getDribbbleShotService(retrofit);
        dribbbleUserService = DribbbleWebServiceHelper.getDribbbleUserService(retrofit);

        simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
    }

    public void testFetchShotsPage() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with page is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsPerPage() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, 5)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with page, per_page is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsListSort() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, 5, Shot.LIST_ANIMATED, Shot.SORT_VIEWS)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with page, per_page, list, sort is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsWithAllParameters() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, 5, Shot.LIST_ANIMATED, Shot.SORT_VIEWS,
                        (simpleDateFormat.format(new Date())), Shot.TIMEFRAME_MONTH)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with all parameters is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }


    public void testFetchShotsWithAllParametersWithOutPerPage() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, Shot.LIST_ANIMATED, Shot.SORT_VIEWS,
                        (simpleDateFormat.format(new Date())), Shot.TIMEFRAME_YEAR)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with page, list, sort, date, timeframe is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsOnlyList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(Shot.LIST_ANIMATED)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with list is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsQueryMap() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("list", Shot.LIST_ANIMATED);
                queryMap.put("sort", Shot.SORT_VIEWS);
                queryMap.put("per_string", 5);
                dribbbleService.fetchShots(queryMap)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchShots with QueryMap is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchSortedShots() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchSortedShots(Shot.SORT_VIEWS)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("fetchSortedShots is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetShot() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getShot(TEST_SHOT_ID)
                        .enqueue(new Callback<Shot>() {
                            @Override
                            public void onResponse(Call<Shot> call, Response<Shot> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<Shot> call, Throwable t) {
                                assertTrue("testGetShot is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testCreateUpdateDeleteShot() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {
            public void run() {

                final String[] tags = new String[]{"tag 1", "tag 2"};
                Shot shot = new Shot();
                shot.setTitle("ShotTitle");
                shot.setDescription("Description for this shot");
                shot.setTags(tags);

                //TODO: Remove path of file
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Download", "ball.jpg");

                if (!file.exists()) {
                    assertTrue("File not exists!", false);
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);


                authorizedDribbbleService.createShot(shot.getTitle(), part)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Map<String, List<String>> headerList = response.headers().toMultimap();
                                boolean isGetShotId = false;
                                long shotId = 0;
                                if (headerList.containsKey("Location")) {
                                    List<String> headerValues = headerList.get("Location");
                                    for (String headerValue : headerValues) {
                                        int lastIndexOfLine = headerValue.lastIndexOf("/");
                                        int fistIndexOfMinus = headerValue.lastIndexOf("-");
                                        String stringShotId = headerValue.substring(lastIndexOfLine + 1, fistIndexOfMinus - 1);
                                        shotId = Long.parseLong(stringShotId);
                                        isGetShotId = true;
                                    }
                                }


//                                for (Header header : headerList) {
//
//                                    String headerName = header.getName();
//
//                                    if (headerName != null) {
//
//                                        if (headerName.equals("Location")) {
//
//                                            // The way to get shot ID from list of headers of response
//
//                                            String headerValue = header.getValue();
//                                            int lastIndexOfLine = headerValue.lastIndexOf("/");
//                                            int fistIndexOfMinus = headerValue.lastIndexOf("-");
//                                            String stringShotId = headerValue.substring(lastIndexOfLine + 1, fistIndexOfMinus);
//                                            shotId = Long.parseLong(stringShotId);
//                                            isGetShotId = true;
//                                        }
//
//                                    }
//
//                                }

                                if (!isGetShotId) {
                                    assertTrue("Error get id from header", false);
                                }
                                Shot shot = new Shot();
                                shot.setId(shotId);
                                shot.setDescription("New Shot Description");

                                authorizedDribbbleService.updateShot(shot.getId(), shot.getDescription())
                                        .enqueue(new Callback<Shot>() {
                                            @Override
                                            public void onResponse(Call<Shot> call, Response<Shot> response) {
                                                assertNotNull(response.body());
//
                                                authorizedDribbbleService.deleteShot(response.body().getId())
                                                        .enqueue(new Callback<Void>() {
                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                signal.countDown();

                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {
                                                                assertTrue("testDeleteShot is failed", false);
                                                                signal.countDown();
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onFailure(Call<Shot> call, Throwable t) {
                                                assertTrue("testUpdateShot is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                assertTrue("testCreateShot is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* Shot Attachments */

    public void testGetShotAttachments() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotAttachments(TEST_SHOT_WITH_ATTACHMENT_ID)
                        .enqueue(new Callback<List<Attachment>>() {
                            @Override
                            public void onResponse(Call<List<Attachment>> call, Response<List<Attachment>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                                assertTrue("testGetShotAttachments is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetShotAttachment() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotAttachment(TEST_SHOT_WITH_ATTACHMENT_ID, TEST_ATTACHMENT_ID)
                        .enqueue(new Callback<Attachment>() {
                            @Override
                            public void onResponse(Call<Attachment> call, Response<Attachment> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<Attachment> call, Throwable t) {
                                assertTrue("testGetShotAttachment is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }


    /* Shot Bucket */

    public void testGetShotBuckets() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotBuckets(TEST_SHOT_ID)
                        .enqueue(new Callback<List<Bucket>>() {
                            @Override
                            public void onResponse(Call<List<Bucket>> call, Response<List<Bucket>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Bucket>> call, Throwable t) {
                                assertTrue("testGetShotBuckets is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();

    }

    /* Shot Comment */

    public void testGetShotComments() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotComments(TEST_SHOT_ID)
                        .enqueue(new Callback<List<Comment>>() {
                            @Override
                            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Comment>> call, Throwable t) {
                                assertTrue("testGetShotComments is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetCommentLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getCommentLikes(TEST_SHOT_ID, TEST_COMMENT_ID)
                        .enqueue(new Callback<List<Like>>() {
                            @Override
                            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Like>> call, Throwable t) {
                                assertTrue("testGetCommentLikes is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetShotComment() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotComment(TEST_SHOT_ID, TEST_COMMENT_ID)
                        .enqueue(new Callback<Comment>() {
                            @Override
                            public void onResponse(Call<Comment> call, Response<Comment> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<Comment> call, Throwable t) {
                                assertTrue("getShotComment is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testCreateUpdateDeleteComment() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  Comment comment = new Comment();
                                  comment.setBody("New comment body");
                                  dribbbleService.createComment(TEST_SHOT_ID, comment)
                                          .enqueue(new Callback<Comment>() {
                                              @Override
                                              public void onResponse(Call<Comment> call, Response<Comment> response) {
                                                  Comment comment = response.body();
                                                  assertNotNull(comment);
                                                  comment.setBody("Updated body of comment");
                                                  dribbbleService.updateShotComment(TEST_SHOT_ID, comment.getId(), comment)
                                                          .enqueue(new Callback<Comment>() {
                                                              @Override
                                                              public void onResponse(Call<Comment> call, Response<Comment> response) {
                                                                  Comment comment = response.body();
                                                                  assertNotNull(comment);
                                                                  dribbbleService.deleteShotComment(TEST_SHOT_ID, comment.getId())
                                                                          .enqueue(new Callback<Void>() {
                                                                              @Override
                                                                              public void onResponse(Call<Void> call, Response<Void> response) {
                                                                                  signal.countDown();
                                                                              }

                                                                              @Override
                                                                              public void onFailure(Call<Void> call, Throwable t) {
                                                                                  assertTrue("deleteShotComment is failed", false);
                                                                                  signal.countDown();
                                                                              }
                                                                          });
                                                              }

                                                              @Override
                                                              public void onFailure(Call<Comment> call, Throwable t) {
                                                                  assertTrue("updateShotComment is failed", false);
                                                                  signal.countDown();
                                                              }
                                                          });
                                              }

                                              @Override
                                              public void onFailure(Call<Comment> call, Throwable t) {
                                                  assertTrue("createComment is failed", false);
                                                  signal.countDown();
                                              }
                                          });
                              }
                          }
        );

        signal.await();
    }

    public void testLikeCheckUnlikeShotComment() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.likeShotComment(TEST_SHOT_ID, TEST_COMMENT_ID)
                        .enqueue(new Callback<Like>() {
                            @Override
                            public void onResponse(Call<Like> call, Response<Like> response) {
                                assertNotNull(response.body());
                                dribbbleService.checkIsLikedShotComment(TEST_SHOT_ID, TEST_COMMENT_ID)
                                        .enqueue(new Callback<Like>() {
                                            @Override
                                            public void onResponse(Call<Like> call, Response<Like> response) {
                                                assertNotNull(response.body());
                                                dribbbleService.unlikeShotComment(TEST_SHOT_ID, TEST_COMMENT_ID)
                                                        .enqueue(new Callback<Void>() {
                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                signal.countDown();
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {
                                                                assertTrue("unlikeShotComment is failed", false);
                                                                signal.countDown();
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onFailure(Call<Like> call, Throwable t) {
                                                assertTrue("checkIsLikedShotComment is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<Like> call, Throwable t) {
                                assertTrue("likeShotComment is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* Shot Like */

    public void testUnlikeLikeCheckShot() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                dribbbleUserService.getAuthenticatedUsersLikes()
                        .enqueue(new Callback<List<Like>>() {
                            @Override
                            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                                List<Like> likes = response.body();
                                assertNotNull(likes);
                                final Shot shot = likes.get(0).getShot();
                                dribbbleService.unlikeShot(shot.getId())
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                dribbbleService.likeShot(shot.getId())
                                                        .enqueue(new Callback<Like>() {
                                                            @Override
                                                            public void onResponse(Call<Like> call, Response<Like> response) {
                                                                assertNotNull(response.body());
                                                                dribbbleService.checkShotIsLiked(shot.getId())
                                                                        .enqueue(new Callback<Like>() {
                                                                            @Override
                                                                            public void onResponse(Call<Like> call, Response<Like> response) {
                                                                                assertNotNull(response.body());
                                                                                signal.countDown();
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Call<Like> call, Throwable t) {
                                                                                assertTrue("checkShotIsLiked is failed", false);
                                                                                signal.countDown();
                                                                            }
                                                                        });
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Like> call, Throwable t) {
                                                                assertTrue("likeShot is failed", false);
                                                                signal.countDown();
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                assertTrue("unlikeShot is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<List<Like>> call, Throwable t) {
                                assertTrue("getAuthenticatedUsersLikes is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testGetShotLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotLikes(TEST_SHOT_ID)
                        .enqueue(new Callback<List<Like>>() {
                            @Override
                            public void onResponse(Call<List<Like>> call, Response<List<Like>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Like>> call, Throwable t) {
                                assertTrue("testGetShotLikes is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* Shot Project */

    public void testGetShotProjectsList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotProjectsList(TEST_SHOT_ID)
                        .enqueue(new Callback<List<Project>>() {
                            @Override
                            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Project>> call, Throwable t) {
                                assertTrue("getShotProjectsList is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    /* Shot Rebound */

    public void testGetShotReboundsList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotReboundsList(TEST_SHOT_ID)
                        .enqueue(new Callback<List<Rebound>>() {
                            @Override
                            public void onResponse(Call<List<Rebound>> call, Response<List<Rebound>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Rebound>> call, Throwable t) {
                                assertTrue("getShotReboundsList is failed", false);
                                t.printStackTrace();
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }
}
