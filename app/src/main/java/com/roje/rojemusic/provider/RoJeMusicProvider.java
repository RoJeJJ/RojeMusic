package com.roje.rojemusic.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.roje.rojemusic.R;
import com.roje.rojemusic.db.RoJeMusicDBHelper;
import com.roje.rojemusic.db.store.SongSheetColumns;

public class RoJeMusicProvider extends ContentProvider {
    public static final String AUTHORITIES = "com.roje.rojemusic.RJMusicProvider";
    private RoJeMusicDBHelper helper;
    private static UriMatcher matcher;


    public static final String recent_music = "/recent_music";
    public static final int rmCode = 1;

    public static final String download_music = "/download_music";
    public static final int dlCode = 2;


    public static final String song_sheet = "/song_sheet";
    public static final int song_sheet_code = 5;
    public static final Uri SONG_SHEET_URI = Uri.parse("content://"+AUTHORITIES+ song_sheet);

    public static final Uri SONG_SHEET_SAME_NAME = Uri.parse("content://"+AUTHORITIES+"/playlist_same_name");

    private ContentResolver resolver;
    private Context context;
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITIES, recent_music,rmCode);
        matcher.addURI(AUTHORITIES, download_music,dlCode);
        matcher.addURI(AUTHORITIES, song_sheet, song_sheet_code);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        switch (matcher.match(uri)){
            case song_sheet_code:
                long id = (long) values.get(SongSheetColumns.id);
                Cursor cursor = helper.getReadableDatabase().query(SongSheetColumns.TABLE_NAME,new String[]{SongSheetColumns.id},
                        SongSheetColumns.id+"="+id,null,null,null,null);
                boolean b = cursor.moveToFirst();
                cursor.close();
                if (b) {
                    return SONG_SHEET_SAME_NAME;
                }else {
                    long i = helper.getWritableDatabase().insert(SongSheetColumns.TABLE_NAME,null,values);
                    if (i > 0){
                        Uri newUri = Uri.withAppendedPath(SONG_SHEET_URI,String.valueOf(i));
                        if (resolver != null)
                            resolver.notifyChange(newUri,null);
                        return newUri;
                    }
                }
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        helper = new RoJeMusicDBHelper(getContext());
        context = getContext();
        if (context != null)
            resolver = context.getContentResolver();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (matcher.match(uri)){
            case song_sheet_code:
                Cursor cursor = helper.getReadableDatabase().query(SongSheetColumns.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                if (cursor.getCount() == 0){
                    ContentValues cValues = new ContentValues();
                    cValues.put(SongSheetColumns.id,0);
                    cValues.put(SongSheetColumns.name,context.getString(R.string.myFavSong));
                    helper.getWritableDatabase().insert(SongSheetColumns.TABLE_NAME,null,cValues);

                    cursor.close();
                    cursor = helper.getReadableDatabase().query(SongSheetColumns.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                }
                cursor.setNotificationUri(resolver,SONG_SHEET_URI);
                return cursor;
        }
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
