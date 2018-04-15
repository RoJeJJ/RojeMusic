package com.roje.rojemusic.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String PREF_COOKIES = "PREF_COOKIES";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String UID="uid";
    private static final String CREATE="create";
    private static final String COLLECT = "collect";

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
    public static boolean getCreateExpand(Context context){
        return context.getSharedPreferences(CREATE,Context.MODE_PRIVATE).getBoolean(CREATE,false);
    }
    public static boolean getCollectExpand(Context context){
        return context.getSharedPreferences(COLLECT,Context.MODE_PRIVATE).getBoolean(COLLECT,false);
    }
    public static void setCreateExpand(Context context,boolean b){
        context.getSharedPreferences(CREATE,Context.MODE_PRIVATE).edit().putBoolean(CREATE,b).apply();
    }
    public static void setCollectExpand(Context context,boolean b){
        context.getSharedPreferences(COLLECT,Context.MODE_PRIVATE).edit().putBoolean(COLLECT,b).apply();
    }
}
