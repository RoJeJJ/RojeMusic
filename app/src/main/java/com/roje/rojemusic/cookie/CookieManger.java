package com.roje.rojemusic.cookie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManger implements CookieJar {


    private static PersistentCookieStore cookieStore;

    public CookieManger(Context context) {
        if (cookieStore == null ) {
            Log.i("application","2");
            cookieStore = new PersistentCookieStore(context);
        }
    }



    @Override
    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
        if (cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
        return cookieStore.get(url);
    }

}
