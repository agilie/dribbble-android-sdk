package com.agilie.dribbblesdk.sample.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import com.google.android.gms.security.ProviderInstaller;

public class DribbbleSdkSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        checkTls();
    }

    // http://stackoverflow.com/questions/29249630/android-enable-tlsv1-2-in-okhttp
    private void checkTls() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                ProviderInstaller.installIfNeededAsync(this, new ProviderInstaller.ProviderInstallListener() {
                    @Override
                    public void onProviderInstalled() {

                    }

                    @Override
                    public void onProviderInstallFailed(int i, Intent intent) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
