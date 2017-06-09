package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.domain.User;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleTeamsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DribbbleTeamsServiceTest extends InstrumentationTestCase {
    protected static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    protected static final long TEST_SHOT_ID = 2132481;

    protected DribbbleShotsService dribbleShotsService;
    protected DribbbleTeamsService dribbbleTeamsService;

    public DribbbleTeamsServiceTest() {
        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(AUTH_TOKEN_FOR_TEST);
        Retrofit retrofit = DribbbleWebServiceHelper.getRetrofitBuilder(okHttpClientBuilder).build();

        dribbleShotsService = DribbbleWebServiceHelper.getDribbbleShotService(retrofit);
        dribbbleTeamsService = DribbbleWebServiceHelper.getDribbbleTeamService(retrofit);
    }

    public void testGetTeamMembersList() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                dribbleShotsService.getShot(TEST_SHOT_ID).enqueue(new Callback<Shot>() {

                    @Override
                    public void onResponse(Call<Shot> call, Response<Shot> response) {
                        long teamId = response.body().getTeam().getId();
                        dribbbleTeamsService.getTeamMembersList(teamId)
                                .enqueue(new Callback<List<User>>() {
                                    @Override
                                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                        assertNotNull(response.body());
                                        signal.countDown();
                                    }

                                    @Override
                                    public void onFailure(Call<List<User>> call, Throwable t) {
                                        assertTrue("getTeamMembersList is failed", false);
                                        signal.countDown();
                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<Shot> call, Throwable t) {
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
                dribbleShotsService.getShot(TEST_SHOT_ID)
                        .enqueue(new Callback<Shot>() {
                            @Override
                            public void onResponse(Call<Shot> call, Response<Shot> response) {
                                long teamId = response.body().getTeam().getId();
                                dribbbleTeamsService.getTeamShotsList(teamId)
                                        .enqueue(new Callback<List<Shot>>() {
                                            @Override
                                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                                assertNotNull(response.body());
                                                signal.countDown();
                                            }

                                            @Override
                                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                                assertTrue("getTeamShotsList is failed", false);
                                                signal.countDown();
                                            }
                                        });
                            }

                            @Override
                            public void onFailure(Call<Shot> call, Throwable t) {
                                assertTrue("getShot is failed in the testTeamShotsList", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

}
