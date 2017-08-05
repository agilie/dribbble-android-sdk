package com.agilie.dribbblesdkrx.services;

import com.agilie.dribbblesdk.domain.Project;
import com.agilie.dribbblesdk.domain.Shot;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface DribbbleProjectsService {

    /**
     * Get a project
     *
     * @param projectId Project ID to receive
     * @return Network operation result
     */
    @GET("projects/{id}")
    Observable<Project> getProject(@Path("id") long projectId);

    /************************************** PROJECT SHOTS ************************************************/

    /**
     * List of shots for a project
     *
     * @param projectId Project ID to be received list for
     * @return Network operation result
     */
    @GET("projects/{id}/shots")
    Observable<List<Shot>> getShotsForProject(@Path("id") long projectId);
}
