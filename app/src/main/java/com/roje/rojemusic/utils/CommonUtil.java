package com.roje.rojemusic.utils;


import android.content.ContentUris;
import android.net.Uri;
import android.util.Base64;

public class CommonUtil {
    public static final int FILE_SIZE = 1024 * 1024;//1MB
    public static final int DURATION = 60 * 1000;//1分钟
    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }
}
