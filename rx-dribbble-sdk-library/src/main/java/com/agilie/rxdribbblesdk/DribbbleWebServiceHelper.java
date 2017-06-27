package com.agilie.rxdribbblesdk;

import android.os.Build;
import android.util.Log;

import com.agilie.dribbblesdk.service.retrofit.Tls12SocketFactory;
import com.agilie.rxdribbblesdk.services.DribbbleBucketsService;
import com.agilie.rxdribbblesdk.services.DribbbleProjectsService;
import com.agilie.rxdribbblesdk.services.DribbbleShotsService;
import com.agilie.rxdribbblesdk.services.DribbbleTeamsService;
import com.agilie.rxdribbblesdk.services.DribbbleUserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DribbbleWebServiceHelper {

    public static String DRIBBBLE_URL = "https://api.dribbble.com/v1/";

    public static DribbbleBucketsService getDribbbleBucketService(Retrofit retrofit) {
        DribbbleBucketsService service = retrofit.create(DribbbleBucketsService.class);
        return service;
    }

    public static DribbbleProjectsService getDribbbleProjectService(Retrofit retrofit) {
        DribbbleProjectsService service = retrofit.create(DribbbleProjectsService.class);
        return service;
    }

    public static DribbbleShotsService getDribbbleShotService(Retrofit retrofit) {
        DribbbleShotsService service = retrofit.create(DribbbleShotsService.class);
        return service;
    }

    public static DribbbleTeamsService getDribbbleTeamService(Retrofit retrofit) {
        DribbbleTeamsService service = retrofit.create(DribbbleTeamsService.class);
        return service;
    }

    public static DribbbleUserService getDribbbleUserService(Retrofit retrofit) {
        DribbbleUserService service = retrofit.create(DribbbleUserService.class);
        return service;
    }

    public static OkHttpClient.Builder getOkHttpClientBuilder(final String authToken) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authRequest = request.newBuilder().addHeader("Authorization", "Bearer " + authToken).build();
                return chain.proceed(authRequest);
            }
        });

        return enableTls12OnPreLollipop(builder);
    }

    public static Retrofit.Builder getRetrofitBuilder(OkHttpClient.Builder builder) {
        return new Retrofit.Builder()
                .baseUrl(DRIBBBLE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }
}
