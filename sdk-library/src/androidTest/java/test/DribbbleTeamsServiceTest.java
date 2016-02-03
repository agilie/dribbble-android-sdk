package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.User;
import com.agilie.dribbblesdk.service.retrofit.DribbbleServiceGenerator;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleTeamsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DribbbleTeamsServiceTest extends InstrumentationTestCase {
    protected static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    protected static final long TEST_SHOT_ID = 2132481;

    protected DribbbleShotsService dribbleShotsService;
    protected DribbbleTeamsService dribbbleTeamsService;

    public DribbbleTeamsServiceTest() {
        dribbleShotsService = DribbbleServiceGenerator.getDribbbleShotService(AUTH_TOKEN_FOR_TEST);
        dribbbleTeamsService = DribbbleServiceGenerator.getDribbbleTeamService(AUTH_TOKEN_FOR_TEST);
    }

    public void testGetTeamMembersList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbleShotsService.getShot(TEST_SHOT_ID, new Callback<Shot>() {
                    @Override
                    public void success(Shot shot, Response response) {
                        long teamId = shot.getTeam().getId();
                        dribbbleTeamsService.getTeamMembersList(teamId, new Callback<List<User>>() {
                            @Override
                            public void success(List<User> users, Response response) {
                                assertNotNull(users);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("getTeamMembersList is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getShot is failed in the testGetTeamMembersList", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

    public void testTeamShotsList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbleShotsService.getShot(TEST_SHOT_ID, new Callback<Shot>() {
                    @Override
                    public void success(Shot shot, Response response) {
                        long teamId = shot.getTeam().getId();
                        dribbbleTeamsService.getTeamShotsList(teamId, new Callback<List<Shot>>() {
                            @Override
                            public void success(List<Shot> shots, Response response) {
                                assertNotNull(shots);
                                signal.countDown();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                assertTrue("getTeamShotsList is failed", false);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("getShot is failed in the testTeamShotsList", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }

}
