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

## Dependencies

- [Retrofit](https://github.com/square/retrofit)
- [Android OAuth Client](https://github.com/wuman/android-oauth-client)

## Author

Agilie info@agilie.com

## License

Dribbble API SDK is available under the MIT License. See the LICENSE.md file for more info.
