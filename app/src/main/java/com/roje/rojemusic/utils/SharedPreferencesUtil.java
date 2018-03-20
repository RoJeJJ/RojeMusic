package com.roje.rojemusic.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String PREF_COOKIES = "PREF_COOKIES";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String UID="uid";

    public static String getPhoneNumber(Context context){
        return context.getSharedPreferences(PHONE_NUMBER,Context.MODE_PRIVATE).getString(PHONE_NUMBER,null);
    }
    public static void setPrePhoneNumber(Context context,String number){
        context.getSharedPreferences(PHONE_NUMBER,Context.MODE_PRIVATE).edit().putString(PHONE_NUMBER,number).apply();
    }
    public static long getUid(Context context){
        return context.getSharedPreferences(UID,Context.MODE_PRIVATE).getLong(UID,-1);
    }
    public static void setUerId(Context context,long userId){
        context.getSharedPreferences(UID,Context.MODE_PRIVATE).edit().putLong(UID,userId).apply();
    }
}
