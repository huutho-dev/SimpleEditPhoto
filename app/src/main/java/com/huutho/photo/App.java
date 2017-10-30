package com.huutho.photo;

import android.app.Application;

/**
 * Created by ThoNh on 10/30/2017.
 */

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
