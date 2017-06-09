package com.agilie.dribbblesdk.service.auth;

import android.text.TextUtils;

import java.util.Collection;

public class AuthCredentials {
    private String clientId;
    private String clientSecret;
    private String clientAccessToken;
    private String redirectUrl;
    private String state;
    private Collection<String> scopes;

    private AuthCredentials(String clientId, String clientSecret, String clientAccessToken, String redirectUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientAccessToken = clientAccessToken;
        this.redirectUrl = redirectUrl;
    }

    public String getOauthUrl() {
        if (TextUtils.isEmpty(state)) {
            return DribbbleConstants.OAUTH_URL;
        } else {
            return DribbbleConstants.OAUTH_URL + String.format(DribbbleConstants.OAUTH_URL_STATE_TEMPLATE, state);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getClientAccessToken() {
        return clientAccessToken;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getState() {
        return state;
    }

    public Collection<String> getScopes() {
        return scopes;
    }

    public static Builder newBuilder(String clientId, String clientSecret, String clientAccessToken, String redirectUrl) {
        return new AuthCredentials(clientId, clientSecret, clientAccessToken, redirectUrl).new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setState(String state) {
            AuthCredentials.this.state = state;
            return this;
        }

        public Builder setScope(Collection<String> scope) {
            AuthCredentials.this.scopes = scope;
            return this;
        }

        public AuthCredentials build() {
            return AuthCredentials.this;
        }
    }
}