package com.agilie.dribbblesdk.sample.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agilie.dribbblesdk.domain.Shot;
import com.agilie.dribbblesdk.service.auth.AuthCredentials;
import com.agilie.dribbblesdk.service.auth.DribbbleAuthHelper;
import com.agilie.dribbblesdk.service.auth.DribbbleConstants;
import com.agilie.dribbblesdk.service.retrofit.DribbbleWebServiceHelper;
import com.google.api.client.auth.oauth2.Credential;

import java.util.Arrays;
import java.util.List;

import agilie.dribbblesdkexample.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DribbbleSdkSampleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_STATE_AUTH_TOKEN = "key_state_auth_token";

    private static final int NUMBER_OF_PAGES = 1;
    private static final int SHOTS_PER_PAGE = 5;

    private static final String DRIBBBLE_CLIENT_ID = "<YOUR CLIENT ID HERE>";
    private static final String DRIBBBLE_CLIENT_SECRET = "<YOUR CLIENT SECRET HERE>";
    private static final String DRIBBBLE_CLIENT_ACCESS_TOKEN = "<YOUR CLIENT ACCESS TOKEN HERE>";
    private static final String DRIBBBLE_CLIENT_REDIRECT_URL = "<YOUR REDIRECT URL HERE>";

    private TextView mTextViewResponse;
    private ProgressBar mProgressBar;

    private String authToken;

    /* Activity Lifecycle */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        mTextViewResponse = (TextView) findViewById(R.id.example_textView_response);
        mProgressBar = (ProgressBar) findViewById(R.id.example_progressBar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString(KEY_STATE_AUTH_TOKEN, authToken);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(KEY_STATE_AUTH_TOKEN)) {
            authToken = savedInstanceState.getString(KEY_STATE_AUTH_TOKEN);
        }
    }

    /* Menu */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem authMenuItem = menu.findItem(R.id.action_auth);
        authMenuItem.setTitle(isLoggedIn() ? R.string.action_bar_log_out : R.string.action_bar_log_in);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_auth: {
                if (isLoggedIn()) {
                    logout();
                } else {
                    login();
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    /* OnClickListener */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.example_button_get_my_shot:
                getMyShot();
                break;
            case R.id.example_button_get_recent_shot:
                getRecentShot();
                break;
            default:
                break;
        }
    }

    /* Network */

    private void getMyShot() {
        onRequestSent();
        DribbbleWebServiceHelper
                .getDribbbleUserService(getRetrofit(authToken))
                .getAuthenticatedUsersShots(NUMBER_OF_PAGES, SHOTS_PER_PAGE)
                .enqueue(new StubCallback<List<Shot>>());
    }

    public void getRecentShot() {
        onRequestSent();
        DribbbleWebServiceHelper
                .getDribbbleShotService(getRetrofit(DRIBBBLE_CLIENT_ACCESS_TOKEN))
                .fetchShots(NUMBER_OF_PAGES, SHOTS_PER_PAGE)
                .enqueue(new StubCallback<List<Shot>>());
    }

    /* Auth */

    private void login() {
        final AuthCredentials credentials = AuthCredentials.newBuilder(
                DRIBBBLE_CLIENT_ID,
                DRIBBBLE_CLIENT_SECRET,
                DRIBBBLE_CLIENT_ACCESS_TOKEN,
                DRIBBBLE_CLIENT_REDIRECT_URL)
                .setScope(Arrays.asList(
                        DribbbleConstants.SCOPE_PUBLIC,
                        DribbbleConstants.SCOPE_WRITE,
                        DribbbleConstants.SCOPE_UPLOAD,
                        DribbbleConstants.SCOPE_COMMENT))
                .build();

        DribbbleAuthHelper.startOauthDialog(DribbbleSdkSampleActivity.this, credentials, new DribbbleAuthHelper.AuthListener() {

            @Override
            public void onSuccess(final Credential credential) {
                authToken = credential.getAccessToken();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        invalidateOptionsMenu();
                        Toast.makeText(DribbbleSdkSampleActivity.this, R.string.toast_logged_in, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                // TODO: handle error here
            }
        });
    }

    public void logout() {
        AuthCredentials credentials = AuthCredentials.newBuilder(
                DRIBBBLE_CLIENT_ID,
                DRIBBBLE_CLIENT_SECRET,
                DRIBBBLE_CLIENT_ACCESS_TOKEN,
                DRIBBBLE_CLIENT_REDIRECT_URL)
                .build();

        authToken = null;
        DribbbleAuthHelper.logout(this, credentials);
        Toast.makeText(DribbbleSdkSampleActivity.this, R.string.toast_logged_out, Toast.LENGTH_LONG).show();
    }

    /* Private helpers */

    private Retrofit getRetrofit(String authToken) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(final String message) {
                mTextViewResponse.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewResponse.append(message);
                    }
                });
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = DribbbleWebServiceHelper.getOkHttpClientBuilder(authToken);
        okHttpClientBuilder.addInterceptor(interceptor);

        return DribbbleWebServiceHelper
                .getRetrofitBuilder(okHttpClientBuilder)
                .build();
    }

    private boolean isLoggedIn() {
        return !TextUtils.isEmpty(authToken);
    }

    private void onRequestSent() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTextViewResponse.setText("");
        mTextViewResponse.setVisibility(View.GONE);
    }

    private void onResponseReceived() {
        mProgressBar.setVisibility(View.GONE);
        mTextViewResponse.setVisibility(View.VISIBLE);
    }

    private class StubCallback<T> implements Callback<T> {


        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            onResponseReceived();
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            onResponseReceived();
        }
    }
}