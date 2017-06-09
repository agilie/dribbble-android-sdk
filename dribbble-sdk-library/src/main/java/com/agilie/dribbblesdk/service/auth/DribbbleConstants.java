package com.agilie.dribbblesdk.service.auth;

public class DribbbleConstants {
    static final String OAUTH_URL = "https://dribbble.com/oauth/authorize";
    static final String OAUTH_URL_STATE_TEMPLATE = "?state=%s";
    static final String TOKEN_URL = "https://dribbble.com/oauth/token";

    static final String USER_ID = "dribbble";
    static final String CREDENTIAL_STORE_NAME = "credential_store_name";

    public static final String SCOPE_PUBLIC = "public";
    public static final String SCOPE_WRITE = "write";
    public static final String SCOPE_UPLOAD = "upload";
    public static final String SCOPE_COMMENT = "comment";
}
