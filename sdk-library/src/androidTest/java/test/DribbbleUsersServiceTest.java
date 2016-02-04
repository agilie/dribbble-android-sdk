package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Bucket;
import com.agilie.dribbblesdk.domain.Followee;
import com.agilie.dribbblesdk.domain.Like;
import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.Team;
import com.agilie.dribbblesdk.domain.User;
import com.agilie.dribbblesdk.service.retrofit.DribbbleServiceGenerator;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DribbbleUsersServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "3f4b08a584a0b7cb770990ca5d83050a9761d48d0611dbfc4b944ecf1cbac7a2";

//    private static final long TEST_USER_ID = 793483;
    private static final long[] TEST_USERS_ID = {107759, 41719, 3518, 108482, 22069};
    private static final long TEST_FOLLOWED_USER_ID = 400583;

    private DribbbleUserService authorizedDribbbleService;
    private DribbbleUserService dribbbleService;

    public DribbbleUsersServiceTest() {
        authorizedDribbbleService = DribbbleServiceGenerator.getDribbbleUserService(AUTH_TOKEN_FOR_TEST);
        dribbbleService = DribbbleServiceGenerator.getDribbbleUserService(AUTH_TOKEN_FOR_TEST);
    }



    public void testGetSingleUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getSingleUser(TEST_USERS_ID[4], new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        assertNotNull(user);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getSingleUser is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testFetchAuthorizedUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                authorizedDribbbleService.fetchAuthenticatedUser(new Callback<User>() {
                    @Override
                    public void success(final User user, Response response) {
                        assertNotNull(user);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("fetchAuthorizaedUser is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    /* USER Buckets */

    public void testGetUsersBuckets() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersBuckets(TEST_USERS_ID[0], new Callback<List<Bucket>>() {
                    @Override
                    public void success(List<Bucket> buckets, Response response) {
                        assertNotNull(buckets);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersBuckets is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthorizedUsersBuckets() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersBuckets(new Callback<List<Bucket>>() {
                    @Override
                    public void success(List<Bucket> buckets, Response response) {
                        assertNotNull(buckets);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getAuthenticatedUsersBuckets is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    /* USER Followers */

    public void testGetUsersFollowers() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersFollowers(TEST_USERS_ID[1], new Callback<List<Followee>>() {
                    @Override
                    public void success(List<Followee> followees, Response response) {
                        assertNotNull(followees);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersFollowers is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthorizedUsersFollowers() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersFollowers(new Callback<List<Followee>>() {
                    @Override
                    public void success(List<Followee> followees, Response response) {
                        assertNotNull(followees);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getAuthenticatedUsersFollowers is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }


    public void testGetCheckFollowingByUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                dribbbleService.getFollowingByUser(TEST_USERS_ID[3], new Callback<List<Followee>>() {
                    @Override
                    public void success(List<Followee> followees, Response response) {
                        assertNotNull(followees);
                        long followeeId = followees.get(0).getUser().getId();

                        dribbbleService.checkUserIsFollowingAnother(TEST_USERS_ID[3], followeeId, new Callback<Void>() {
                            @Override
                            public void success(Void aVoid, Response response) {
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("checkUserIsFollowingAnother is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getFollowingByUser is failed", false);
                        signal.countDown();
                    }
                });
            }
        });
        signal.await();
    }

    public void testGetCheckFollowingByCurrentUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getFollowingByCurrentUser(new Callback<List<Followee>>() {
                    @Override
                    public void success(List<Followee> followees, Response response) {
                        assertNotNull(followees);
                        long followeeId = followees.get(0).getUser().getId();

                        authorizedDribbbleService.checkUserIsFollowed(followeeId, new Callback<Void>() {
                            @Override
                            public void success(Void aVoid, Response response) {
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("checkUserIsFollowed is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getFollowingByCurrentUser is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testShotsForUserFollowedByUser() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.shotsForUserFollowedByUser(new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("shotsForUserFollowedByUser is failed", false);
                        signal.countDown();

                    }
                });
            }
        });

        signal.await();
    }

    public void testFollowUnFollowUser() throws Throwable{
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {

                authorizedDribbbleService.unfollowUser(TEST_FOLLOWED_USER_ID, new Callback<Void>() {
                    @Override
                    public void success(Void aVoid, Response response) {

                        authorizedDribbbleService.followUser(TEST_FOLLOWED_USER_ID, new Callback<Void>() {

                            @Override
                            public void success(Void aVoid, Response response) {
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("followUser is failed", false);
                                signal.countDown();
                            }

                        });

                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("unfollowUser is failed", false);
                        signal.countDown();
                    }
                });

            }
        });

        signal.await();
    }

    /* USER Likes */

    public void testGetUsersLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersLikes(TEST_USERS_ID[0], new Callback<List<Like>>() {
                    @Override
                    public void success(List<Like> likes, Response response) {
                        assertNotNull(likes);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersLikes is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersLikes() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersLikes(new Callback<List<Like>>() {
                    @Override
                    public void success(List<Like> likes, Response response) {
                        assertNotNull(likes);
                        signal.countDown();
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

    /* USER Projects */

    public void testGetUsersProjects() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersProjects(TEST_USERS_ID[0], new Callback<List<Project>>() {
                    @Override
                    public void success(List<Project> projects, Response response) {
                        assertNotNull(projects);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersProjects is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersProjects() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersProjects(new Callback<List<Project>>() {
                    @Override
                    public void success(List<Project> projects, Response response) {
                        assertNotNull(projects);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getAuthenticatedUsersProjects is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    /* USER Shots */

    public void testGetUsersShots() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersShots(TEST_USERS_ID[0], new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersShots is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersShots() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersShots(1, new Callback<List<Shot>>() {
                    @Override
                    public void success(List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getAuthenticatedUsersShots is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    /* USER Teams */

    public void testGetUsersTeams() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbbleService.getUsersTeams(TEST_USERS_ID[0], new Callback<List<Team>>() {
                    @Override
                    public void success(List<Team> teams, Response response) {
                        assertNotNull(teams);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getUsersTeams is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testGetAuthenticatedUsersTeams() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                authorizedDribbbleService.getAuthenticatedUsersTeams(1, new Callback<List<Team>>() {
                    @Override
                    public void success(List<Team> teams, Response response) {
                        assertNotNull(teams);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getAuthenticatedUsersTeams is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

}
