package com.agilie.dribbblesdk.service.retrofit;

import com.agilie.dribbblesdk.service.retrofit.services.DribbbleBucketsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleProjectsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleShotsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleTeamsService;
import com.agilie.dribbblesdk.service.retrofit.services.DribbbleUserService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class DribbbleServiceGenerator {

    public static String DRIBBBLE_URL = "https://api.dribbble.com/v1/";


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

    private static Retrofit getRestAdapter(final String authToken) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authRequest = request.newBuilder().addHeader("Authorization", "Bearer " + authToken).build();
                return chain.proceed(authRequest);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(DRIBBBLE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
