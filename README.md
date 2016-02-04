# Dribbble API SDK

Dribbble Android SDK is an unofficial wrapper for [Dribbble API v1](http://developer.dribbble.com/v1/).

## Link to iOS repo

Check out our [Dribbble iOS SDK](https://github.com/agilie/dribbble-ios-sdk)

## Download

- <b>Android Studio</b>

    To use Dribbble API SDK, clone the project and import oAuth and sdk-library modules into your project.

- <b>Maven</b>

    Coming soon

- <b>Gradle</b>

    Coming soon

## Requirements

Android 2.3+ (API level 9+)

## Quick Start

Please follow sdk usage example (sdk-sample module). It demonstrates how to login, logout and call API methods provided by SDK.
Don't forget to fill the credentials to use Dribbble API:
```java
private static final String DRIBBBLE_CLIENT_ID = "<YOUR CLIENT ID HERE>";
private static final String DRIBBBLE_CLIENT_SECRET = "<YOUR CLIENT SECRET HERE>";
private static final String DRIBBBLE_CLIENT_ACCESS_TOKEN = "<YOUR CLIENT ACCESS TOKEN HERE>";
private static final String DRIBBBLE_CLIENT_REDIRECT_URL = "<YOUR REDIRECT URL HERE>";
```
Authorization isn't required, you can use non-authorized access for some methods. In this case, API client uses default access token provided by Dribbble. You can get the app access token, client id and secret keys on your Dribbble app page, check out the next links: 

- Register your app on https://dribbble.com/account/applications/new

- Documentation: http://developer.dribbble.com/v1/

## API usage

Use DribbbleAuthHelper.java class for authorization:
- Log into your Dribbble's account using startOauthDialog method:
```java
DribbbleAuthHelper.startOauthDialog(ExampleActivity.this, credentials, new DribbbleAuthHelper.AuthListener() {

            @Override
            public void onSuccess(final Credential credential) {
                String authToken = credential.getAccessToken();
                
                // TODO: Handle success login event
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                
                // TODO: Handle error here
            }
        });
```

- Logout is also easy:
```java
AuthCredentials credentials = AuthCredentials.newBuilder(
                DRIBBBLE_CLIENT_ID,
                DRIBBBLE_CLIENT_SECRET,
                DRIBBBLE_CLIENT_ACCESS_TOKEN,
                DRIBBBLE_CLIENT_REDIRECT_URL)
                .build();

DribbbleAuthHelper.logout(YourActivity.this, credentials);
```

All Dribbble's API methods are mapped through Retrofit interfaces:
- DribbbleBucketsService.java
```java
@GET("buckets/{id}")
Call<Bucket> getBucket(@Path("id") long bucketId);

@POST("buckets")
Call<Bucket> createBucket(@Body Bucket bucket);

@PUT("buckets/{id}")
Call<Bucket> updateBucket(@Path("id") long bucketId, @Body Bucket bucket);

@DELETE("buckets/{id}")
Call<Void> deleteBucket(@Path("id") long bucketId);

@GET("buckets/{id}/shots")
Call<List<Shot>> getShotsForBucket(@Path("id") long bucketId);

@PUT("buckets/{id}/shots")
Call<Void> addShotToBucket(@Path("id") long bucketId, @Query("shot_id") long shotId);

@DELETE("buckets/{id}/shots")
Call<Void> removeShotFromBucket(@Path("id") long bucketId, @Query("shot_id") long shotId);
```

- DribbbleProjectsService.java
```java
@GET("projects/{id}")
Call<Project> getProject(@Path("id") long projectId);

@GET("projects/{id}/shots")
Call<List<Shot>> getShotsForProject(@Path("id") long projectId);
```

- DribbbleShotsService.java
```java
@GET("shots")
Call<List<Shot>> fetchShots(@Query("page") int page);

@GET("shots")
Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage);

@GET("shots")
Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort);

@GET("shots")
Call<List<Shot>> fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe);

@GET("shots")
Call<List<Shot>> fetchShots(@Query("page") int page, @Query("list") String list, @Query("sort") String sort, @Query("date") String date, @Query("timeframe") String timeframe);

@GET("shots")
Call<List<Shot>> fetchShots(@Query("list") String list);

@GET("shots")
Call<List<Shot>> fetchShots(@QueryMap Map<String, Object> parameters);

@GET("shots")
Call<List<Shot>> fetchSortedShots(@Query("sort") String sort);

@GET("shots/{id}")
Call<Shot> getShot(@Path("id") long shotId);

@Multipart
@POST("shots")
Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image, @Part("description") String description,
                          @Part("tags") String[] tags, @Part("team_id") int teamId, @Part("rebound_source_id") int reboundSourceId);
                          
@Multipart
@POST("shots")
Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image, @Part("description") String description,
                          @Part("tags") String[] tags);
                          
@Multipart
@POST("shots")
Call<Void> createShot(@Part("title") String title, @Part("image") RequestBody image);

@Multipart
@POST("shots")
Call<Void> createShot(@PartMap Map<String, Object> partMap);

@Multipart
@PUT("shots/{id}")
Call<Shot> updateShot(@Path("id") long shotId, @Part("title") String title, @Part("description") String description,
                          @Part("team_id") int teamId, @Part("tags") String[] tags);
                          
@DELETE("shots/{id}")
Call<Void> deleteShot(@Path("id") long shotId);

@GET("shots/{id}/attachments")
Call<List<Attachment>> getShotAttachments(@Path("id") long shotId);

@POST("shots/{shot}/attachments")
Call<Void> createShotAttachment(@Path("shot") long shotId, @Part("file") RequestBody file);

@GET("shots/{shot}/attachments/{id}")
Call<Attachment> getShotAttachment(@Path("shot") long shotId, @Path("id") long attachmentId);

@DELETE("shots/{shot}/attachments/{id}")
Call<Void> deleteShotAttachment(@Path("shot") long shotId, @Path("id") long attachmentId);

@GET("shots/{id}/buckets")
Call<List<Bucket>> getShotBuckets(@Path("id") long shotId);

@GET("shots/{shot}/comments")
Call<List<Comment>> getShotComments(@Path("shot") long shotId);

@GET("shots/{shot}/comments/{id}/likes")
Call<List<Like>> getCommentLikes(@Path("shot") long shotId, @Path("id") long commentId);

@POST("shots/{shot}/comments")
Call<Comment> createComment(@Path("shot") long shotId, @Body Comment body);

@GET("shots/{shot}/comments/{id}")
Call<Comment> getShotComment(@Path("shot") long shotId, @Path("id") long commentId);

@PUT("shots/{shot}/comments/{id}")
Call<Comment> updateShotComment(@Path("shot") long shotId, @Path("id") long commentId, @Body Comment comment);

@DELETE("shots/{shot}/comments/{id}")
Call<Void> deleteShotComment(@Path("shot") long shotId, @Path("id") long commentId);

@GET("shots/{shot}/comments/{id}/like")
Call<Like> checkIsLikedShotComment(@Path("shot") long shotId, @Path("id") long commentId);

@POST("shots/{shot}/comments/{id}/like")
Call<Like> likeShotComment(@Path("shot") long shotId, @Path("id") long commentId);

@DELETE("shots/{shot}/comments/{id}/like")
Call<Void> unlikeShotComment(@Path("shot") long shotId, @Path("id") long commentId);

@GET("shots/{id}/likes")
Call<List<Like>> getShotLikes(@Path("id") long shotId);

@GET("shots/{id}/like")
Call<Like> checkShotIsLiked(@Path("id") long shotId);

@POST("shots/{id}/like")
Call<Like> likeShot(@Path("id") long shotId);

@DELETE("shots/{id}/like")
Call<Void> unlikeShot(@Path("id") long shotId);

@GET("shots/{id}/projects")
Call<List<Project>> getShotProjectsList(@Path("id") long shotId);

@GET("shots/{id}/rebounds")
Call<List<Rebound>> getShotReboundsList(@Path("id") long shotId);
```

- DribbbleTeamsService.java
```java
@GET("teams/{team}/members")
Call<List<User>> getTeamMembersList(@Path("team") long teamId);

@GET("teams/{team}/shots")
Call<List<Shot>> getTeamShotsList(@Path("team") long teamId);
```

- DribbbleUserService.java
```java
@GET("users/{user}")
Call<User> getSingleUser(@Path("user") long userId);

@GET("user")
Call<User> fetchAuthenticatedUser();

@GET("users/{user}/buckets")
Call<List<Bucket>> getUsersBuckets(@Path("user") long userId);

@GET("user/buckets")
Call<List<Bucket>> getAuthenticatedUsersBuckets();

@GET("users/{user}/followers")
Call<List<Follower>> getUsersFollowers(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

@GET("user/followers")
Call<List<Follower>> getAuthenticatedUsersFollowers(@Query("page") int page, @Query("per_page") int perPage);

@GET("users/{user}/following")
Call<List<Followee>> getFollowingByUser(@Path("user") long userId);

@GET("users/{user}/following")
Call<List<Followee>> getFollowingByUser(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

@GET("user/following")
Call<List<Followee>> getFollowingByCurrentUser();

@GET("user/following/shots")
Call<List<Shot>> shotsForUserFollowedByUser();

@GET("user/following/{user}")
Call<Void> checkUserIsFollowed(@Path("user") long userId);

@GET("users/{user}/following/{target_user}")
Call<Void> checkUserIsFollowingAnother(@Path("user") long userId, @Path("target_user") long targetUserId);

@PUT("users/{id}/follow")
Call<Void> followUser(@Path("id") long userId);

@DELETE("users/{id}/follow")
Call<Void> unfollowUser(@Path("id") long userId);

@GET("users/{user}/likes")
Call<List<Like>> getUsersLikes(@Path("user") long userId);

@GET("users/{user}/likes")
Call<List<Like>> getUsersLikes(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

@GET("user/likes")
Call<List<Like>> getAuthenticatedUsersLikes();

@GET("users/{user}/projects")
Call<List<Project>> getUsersProjects(@Path("user") long userId);

@GET("user/projects")
Call<List<Project>> getAuthenticatedUsersProjects();

@GET("users/{user}/shots")
Call<List<Shot>> getUsersShots(@Path("user") long userId);

@GET("users/{user}/shots")
Call<List<Shot>> getUsersShots(@Path("user") long userId, @Query("page") int page, @Query("per_page") int perPage);

@GET("user/shots")
Call<List<Shot>> getAuthenticatedUsersShots(@Query("page") int page, @Query("per_page") int perPage);

@GET("users/{user}/teams")
Call<List<Team>> getUsersTeams(@Path("user") long userId);

@GET("user/teams")
Call<List<Team>> getAuthenticatedUsersTeams(@Query("page") int page);
```

## Dependencies

- [Retrofit](https://github.com/square/retrofit)
- [Android OAuth Client](https://github.com/wuman/android-oauth-client)

## Author

Agilie info@agilie.com

## License

Dribbble API SDK is available under the MIT License. See the LICENSE.md file for more info.
