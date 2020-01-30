package co.skreel.example;

import android.app.Application;

import co.skreel.android.networking.SkreelSDK;

public class SkreelApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkreelSDK.getInstance(this);
    }
}
