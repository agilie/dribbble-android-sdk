package com.agilie.dribbblesdk.service.auth;

import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.AuthorizationUIController;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.implicit.ImplicitResponseUrl;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;
import java.util.concurrent.CancellationException;

public class DribbbleAuthHelper {

    public interface AuthListener {
        void onSuccess(Credential credential);

        void onError(Exception ex);
    }

    public static void startOauthDialog(final FragmentActivity activity,
                                        final AuthCredentials credentials,
                                        final AuthListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    final Credential credential = getOauth(activity, credentials).authorizeExplicitly(DribbbleConstants.USER_ID, null, null).getResult();
                    if (listener != null) {
                        listener.onSuccess(credential);
                    }
                } catch (IOException | CancellationException ex) {
                    if (listener != null) {
                        listener.onError(ex);
                    }
                }
            }
        }.start();
    }

    private static OAuthManager getOauth(FragmentActivity activity, final AuthCredentials credentials) {
        SharedPreferencesCredentialStore credentialStore = new SharedPreferencesCredentialStore(
                activity,
                DribbbleConstants.CREDENTIAL_STORE_NAME,
                new JacksonFactory());

        AuthorizationFlow.Builder builder = new AuthorizationFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new GenericUrl(DribbbleConstants.TOKEN_URL),
                new ClientParametersAuthentication(credentials.getClientId(), credentials.getClientSecret()),
                credentials.getClientId(),
                credentials.getOauthUrl());

        if (credentials.getScopes() != null) {
            builder.setScopes(credentials.getScopes());
        }
        builder.setCredentialStore(credentialStore);
        AuthorizationFlow flow = builder.build();

        final AuthorizationUIController controller =
                new DialogFragmentController(activity.getSupportFragmentManager()) {

                    @Override
                    public String getRedirectUri() throws IOException {
                        return credentials.getRedirectUrl();
                    }

                    @Override
                    public boolean isJavascriptEnabledForWebView() {
                        return true;
                    }

                    @Override
                    public ImplicitResponseUrl waitForImplicitResponseUrl() throws IOException {
                        return super.waitForImplicitResponseUrl();
                    }
                };

        OAuthManager oauth = new OAuthManager(flow, controller);
        return oauth;
    }

    public static boolean logout(FragmentActivity activity, AuthCredentials credentials) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().removeAllCookies(null);
            } else {
                CookieSyncManager.createInstance(activity);
                CookieManager.getInstance().removeAllCookie();
            }
            return getOauth(activity, credentials).deleteCredential(DribbbleConstants.USER_ID, null, null).getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
