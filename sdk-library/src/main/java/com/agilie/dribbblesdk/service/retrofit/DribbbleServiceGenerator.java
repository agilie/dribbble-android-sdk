package com.agilie.dribbblesdk.service.retrofit;

import com.agilie.dribbblesdk.service.retrofit.services.DribbbleBucketsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleProjectsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleTeamsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class DribbbleServiceGenerator {

    public static String DRIBBBLE_URL = "https://api.dribbble.com/v1";


    public static DribbbleBucketsService getDribbbleBucketService(final String authToken) {
        DribbbleBucketsService service = getRestAdapter(authToken).create(DribbbleBucketsService.class);
        return service;
    }

    public static DribbbleProjectsService getDribbbleProjectService(final String authToken) {
        DribbbleProjectsService service = getRestAdapter(authToken).create(DribbbleProjectsService.class);
        return service;
    }

    public static DribbbleShotsService getDribbbleShotService(final String authToken) {
        DribbbleShotsService service = getRestAdapter(authToken).create(DribbbleShotsService.class);
        return service;
    }

    public static DribbbleTeamsService getDribbbleTeamService(final String authToken) {
        DribbbleTeamsService service = getRestAdapter(authToken).create(DribbbleTeamsService.class);
        return service;
    }

    public static DribbbleUserService getDribbbleUserService(final String authToken) {
        DribbbleUserService service = getRestAdapter(authToken).create(DribbbleUserService.class);
        return service;
    }

    private static RestAdapter getRestAdapter(final String authToken) {
        return new RestAdapter.Builder()
                    .setEndpoint(DRIBBBLE_URL)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addHeader("Authorization", "Bearer " + authToken);
                        }
                    })
                    .build();
    }
}
