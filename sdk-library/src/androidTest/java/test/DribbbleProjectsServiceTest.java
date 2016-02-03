package test;

import android.test.InstrumentationTestCase;

import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.retrofit.DribbbleServiceGenerator;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleProjectsService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DribbbleProjectsServiceTest extends InstrumentationTestCase {

    private static final String AUTH_TOKEN_FOR_TEST = "55f066c4782e254aa6b6ce516784df3a98b03f22ce42bd7f7495ba2355cda14e";
    private static final long TEST_PROJECT_ID = 273842;

    private DribbbleProjectsService dribbbleService;

    public DribbbleProjectsServiceTest() {
        dribbbleService = DribbbleServiceGenerator.getDribbbleProjectService(AUTH_TOKEN_FOR_TEST);
    }

    public void testGetProject() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            public void run() {
                dribbbleService.getProject(TEST_PROJECT_ID, new Callback<Project>() {
                    @Override
                    public void success(final Project project, Response response) {
                        assertNotNull(project);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
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
                dribbbleService.getShotsForProject(TEST_PROJECT_ID, new Callback<List<Shot>>() {
                    @Override
                    public void success(final List<Shot> shots, Response response) {
                        assertNotNull(shots);
                        signal.countDown();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        assertTrue("testShotsForProject is failed", false);
                        signal.countDown();
                    }
                });
            }
        });

        signal.await();
    }
}
