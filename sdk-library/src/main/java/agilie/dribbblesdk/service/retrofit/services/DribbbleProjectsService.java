package agilie.dribbblesdk.service.retrofit.services;

import java.util.List;

import agilie.dribbblesdk.domain.Project;
import agilie.dribbblesdk.domain.Shot;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DribbbleProjectsService {

    /**
     * Get a project
     *
     * @param projectId     Project ID to receive
     * @param callback      Network operation result
     */
    @GET("/projects/{id}")
    void getProject(@Path("id")long projectId, Callback<Project> callback);

    /************************************** PROJECT SHOTS ************************************************/

    /**
     * List of shots for a project
     *
     * @param projectId     Project ID to be received list for
     * @param callback      Network operation result
     */
    @GET("/projects/{id}/shots")
    void getShotsForProject(@Path("id")long projectId, Callback<List<Shot>> callback);
}
