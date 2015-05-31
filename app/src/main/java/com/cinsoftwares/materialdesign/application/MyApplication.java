package com.cinsoftwares.materialdesign.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by rahul on 5/31/15.
 */
public class MyApplication extends Application {


    private static MyApplication application = null;
    //public static final String ROTTEN_TOMATOES_API_KEY = ""; // My Api KEY ...
    public static final String ROTTEN_TOMATOES_API_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

    public static MyApplication getInstance() { return application; }

    public static Context getAppContext() { return application.getApplicationContext(); }


}
