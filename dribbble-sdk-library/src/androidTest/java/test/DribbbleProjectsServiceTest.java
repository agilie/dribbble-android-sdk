package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleProjectsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DribbbleProjectsServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    private static final long TEST_PROJECT_ID = 48926;

    private DribbbleProjectsService dribbbleService;
    public DribbbleProjectsServiceTest() {
        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(AUTH_TOKEN_FOR_TEST);
        Retrofit retrofit = DribbbleWebServiceHelper.getRetrofitBuilder(okHttpClientBuilder).build();
        dribbbleService = DribbbleWebServiceHelper.getDribbbleProjectService(retrofit);
    }

    public void testGetProject() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getProject(TEST_PROJECT_ID)
                        .enqueue(new Callback<Project>() {
                            @Override
                            public void onResponse(Call<Project> call, Response<Project> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<Project> call, Throwable t) {
                                assertTrue("testGetProject is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }

    public void testShotsForProject() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getShotsForProject(TEST_PROJECT_ID)
                        .enqueue(new Callback<List<Shot>>() {
                            @Override
                            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                                assertNotNull(response.body());
                                signal.countDown();
                            }

                            @Override
                            public void onFailure(Call<List<Shot>> call, Throwable t) {
                                assertTrue("testShotsForProject is failed", false);
                                signal.countDown();
                            }
                        });
            }
        });

        signal.await();
    }
}
