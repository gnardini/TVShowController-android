package com.gnardini.tvshowcontroller;

import android.app.Application;
import android.content.Context;

public class TvShowControllerApp extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
