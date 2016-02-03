package test;

import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Attachment;
import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Comment;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Rebound;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleServiceGenerator;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class DribbbleShotsServiceTest extends InstrumentationTestCase {

    protected static final String AUTH_TOKEN_FOR_TEST = "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2";
    // token with all scopes "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2"
    // 3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2
    protected static final long TEST_SHOT_ID = 1997749;
    protected static final long TEST_SHOT_WITH_ATTACHMENT_ID = 1999342;
    protected static final long TEST_ATTACHMENT_ID = 351491; // for shot 1999342

    protected static final long TEST_COMMENT_ID = 1146540; // refer to TEST_SHOT_ID

    protected static final String DATE_PATTERN = "yyyy-MM-dd";
    protected DribbbleShotsService authorizedDribbbleService;
    protected DribbbleShotsService dribbbleService;
    protected DribbbleUserService dribbbleUserService;
    protected SimpleDateFormat simpleDateFormat;

    public DribbbleShotsServiceTest() {
        authorizedDribbbleService = DribbbleServiceGenerator.getDribbbleShotService(AUTH_TOKEN_FOR_TEST);
        dribbbleService = DribbbleServiceGenerator.getDribbbleShotService(AUTH_TOKEN_FOR_TEST);
        dribbbleUserService = DribbbleServiceGenerator.getDribbbleUserService(AUTH_TOKEN_FOR_TEST);
        simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
    }

    public void testFetchShotsPage() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("fetchShots with page is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testFetchShotsPerPage() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, 5, new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("fetchShots with page, per_page is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testFetchShotsListSort() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, 5, Shot.LIST_ANIMATED, Shot.SORT_VIEWS, new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                        (simpleDateFormat.format(new Date())), Shot.TIMEFRAME_MONTH,
                        new Callback<List<Shot>>() {
                            @Override
                            public void success(List<Shot> shots, Response response) {
                                assertNotNull(shots);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("fetchShots with all parameters is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }



    public void testFetchShotsWithAllParametersWithOutPerPage() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(1, Shot.LIST_ANIMATED, Shot.SORT_VIEWS,
                        (simpleDateFormat.format(new Date())), Shot.TIMEFRAME_YEAR,
                        new Callback<List<Shot>>() {
                            @Override
                            public void success(List<Shot> shots, Response response) {
                                assertNotNull(shots);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("fetchShots with page, list, sort, date, timeframe is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsOnlyList() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchShots(Shot.LIST_ANIMATED,
                        new Callback<List<Shot>>() {
                            @Override
                            public void success(List<Shot> shots, Response response) {
                                assertNotNull(shots);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("fetchShots with list is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testFetchShotsQueryMap() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> queryMap = new HashMap<>();
                queryMap.put("list", Shot.LIST_ANIMATED);
                queryMap.put("sort", Shot.SORT_VIEWS);
                queryMap.put("per_string", 5);
                dribbbleService.fetchShots(queryMap, new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("fetchShots with QueryMap is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testFetchSortedShots() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.fetchSortedShots(Shot.SORT_VIEWS,
                        new Callback<List<Shot>>() {
                            @Override
                            public void success(List<Shot> shots, Response response) {
                                assertNotNull(shots);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
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
                dribbbleService.getShot(TEST_SHOT_ID, new Callback<Shot>() {
                    @Override
                    public void success(Shot shot, Response response) {
                        assertNotNull(shot);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Download/ball.jpg");

                if (!file.exists()) {
                    assertTrue("File not exists!", false);
                }

                TypedFile typedFile = new TypedFile("image/jpeg", file);

                authorizedDribbbleService.createShot(shot.getTitle(), typedFile, new Callback<Void>() {

                    @Override
                    public void success(Void aVoid, Response response) {

                        List<Header> headerList =  response.getHeaders();
                        boolean isGetShotId = false;
                        long shotId = 0;
                        for (Header header: headerList) {

                            String headerName = header.getName();

                            if (headerName != null) {

                                if (headerName.equals("Location")) {

                                    // The way to get shot ID from list of headers of response

                                    String headerValue = header.getValue();
                                    int lastIndexOfLine = headerValue.lastIndexOf("/");
                                    int fistIndexOfMinus = headerValue.lastIndexOf("-");
                                    String stringShotId = headerValue.substring(lastIndexOfLine + 1, fistIndexOfMinus);
                                    shotId = Long.parseLong(stringShotId);
                                    isGetShotId = true;
                                }

                            }

                        }
                        if (!isGetShotId) {
                            assertTrue("Error get id from header", false);
                        }
                        Shot shot = new Shot();
                        shot.setId(shotId);
                        shot.setDescription("New Shot Description");

                        authorizedDribbbleService.updateShot(shot.getId(), shot.getDescription(), new Callback<Shot>() {

                            @Override
                            public void success(Shot shot, Response response) {

                                assertNotNull(shot);

                                authorizedDribbbleService.deleteShot(shot.getId(), new Callback<Void>() {

                                    @Override
                                    public void success(Void aVoid, Response response) {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        assertTrue("testDeleteShot is failed", false);
                                        signal.countDown();
                                    }
                                });

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("testUpdateShot is failed", false);
                                signal.countDown();
                            }
                        });

                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotAttachments(TEST_SHOT_WITH_ATTACHMENT_ID, new Callback<List<Attachment>>() {
                    @Override
                    public void success(List<Attachment> attachments, Response response) {
                        assertNotNull(attachments);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotAttachment(TEST_SHOT_WITH_ATTACHMENT_ID, TEST_ATTACHMENT_ID, new Callback<Attachment>() {
                    @Override
                    public void success(Attachment attachment, Response response) {
                        assertNotNull(attachment);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotBuckets(TEST_SHOT_ID, new Callback<List<Bucket>>() {
                    @Override
                    public void success(List<Bucket> buckets, Response response) {
                        assertNotNull(buckets);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("testGetShotBuckets is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();

    }

    /* Shot Comment */

    public void testGetShotComments() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getShotComments(TEST_SHOT_ID, new Callback<List<Comment>>() {
                    @Override
                    public void success(List<Comment> comments, Response response) {
                        assertNotNull(comments);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getCommentLikes(TEST_SHOT_ID, TEST_COMMENT_ID, new Callback<List<Like>>() {
                    @Override
                    public void success(List<Like> likes, Response response) {
                        assertNotNull(likes);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotComment(TEST_SHOT_ID, TEST_COMMENT_ID, new Callback<Comment>() {
                    @Override
                    public void success(Comment comment, Response response) {
                        assertNotNull(comment);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.createComment(TEST_SHOT_ID, comment, new Callback<Comment>() {
                    @Override
                    public void success(Comment comment, Response response) {
                        assertNotNull(comment);
                        comment.setBody("Updated body of comment");
                        dribbbleService.updateShotComment(TEST_SHOT_ID, comment.getId(), comment, new Callback<Comment>() {
                            @Override
                            public void success(Comment comment, Response response) {
                                assertNotNull(comment);
                                dribbbleService.deleteShotComment(TEST_SHOT_ID, comment.getId(), new Callback<Void>() {
                                    @Override
                                    public void success(Void aVoid, Response response) {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        assertTrue("deleteShotComment is failed", false);
                                        signal.countDown();
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("updateShotComment is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("createComment is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testLikeCheckUnlikeShotComment() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.likeShotComment(TEST_SHOT_ID, TEST_COMMENT_ID, new Callback<Like>() {

                    @Override
                    public void success(Like like, Response response) {
                        assertNotNull(like);

                        dribbbleService.checkIsLikedShotComment(TEST_SHOT_ID, TEST_COMMENT_ID, new Callback<Like>() {

                            @Override
                            public void success(Like like, Response response) {
                                assertNotNull(like);

                                dribbbleService.unlikeShotComment(TEST_SHOT_ID, TEST_COMMENT_ID, new Callback<Void>() {
                                    @Override
                                    public void success(Void aVoid, Response response) {
                                        signal.countDown();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        assertTrue("unlikeShotComment is failed", false);
                                        signal.countDown();
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("checkIsLikedShotComment is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
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

                dribbbleUserService.getAuthenticatedUsersLikes(new Callback<List<Like>>() {
                    @Override
                    public void success(List<Like> likes, Response response) {
                        assertNotNull(likes);
                        final Shot shot = likes.get(0).getShot();

                        dribbbleService.unlikeShot(shot.getId(), new Callback<Void>() {
                            @Override
                            public void success(Void aVoid, Response response) {

                                dribbbleService.likeShot(shot.getId(), new Callback<Like>() {
                                    @Override
                                    public void success(Like like, Response response) {
                                        assertNotNull(like);

                                        dribbbleService.checkShotIsLiked(shot.getId(), new Callback<Like>() {
                                            @Override
                                            public void success(Like like, Response response) {
                                                assertNotNull(like);
                                                signal.countDown();
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                assertTrue("checkShotIsLiked is failed", false);
                                                signal.countDown();
                                            }
                                        });
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        assertTrue("likeShot is failed", false);
                                        signal.countDown();
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("unlikeShot is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotLikes(TEST_SHOT_ID, new Callback<List<Like>>() {
                    @Override
                    public void success(List<Like> likes, Response response) {
                        assertNotNull(likes);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotProjectsList(TEST_SHOT_ID, new Callback<List<Project>>() {
                    @Override
                    public void success(List<Project> projects, Response response) {
                        assertNotNull(projects);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotReboundsList(TEST_SHOT_ID, new Callback<List<Rebound>>() {
                    @Override
                    public void success(List<Rebound> rebounds, Response response) {
                        assertNotNull(rebounds);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getShotReboundsList is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }
}
