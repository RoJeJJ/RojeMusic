package com.roje.rojemusic;

import android.app.Application;
import android.util.Log;


import com.roje.rojemusic.utils.DisplayUtil;

import java.lang.ref.WeakReference;


public class RjApplication extends Application {
    public static WeakReference<Application> application;
    public static boolean login = false;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("application","1");
       application = new WeakReference<Application>(this);
    }
}
