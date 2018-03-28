package com.roje.rojemusic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.roje.rojemusic.db.store.DownloadFileStoreColumns;
import com.roje.rojemusic.db.store.RecentStoreColumns;
import com.roje.rojemusic.db.store.SearchHistoryColumns;
import com.roje.rojemusic.db.store.SongSheetColumns;


public class RoJeMusicDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "rjmusic.db";
    public RoJeMusicDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+ SongSheetColumns.TABLE_NAME+" (" +
                SongSheetColumns.id +" long not null," +
                SongSheetColumns.name +" char not null," +
                SongSheetColumns.trackNumberUpdateTime+" long  def "+System.currentTimeMillis()+","+
                SongSheetColumns.status+" int not null def 0,"+
                SongSheetColumns.userId +" int not null def 0," +
                SongSheetColumns.createTime+" long not null def "+System.currentTimeMillis()+"," +
                SongSheetColumns.updateTime+" long not null def "+System.currentTimeMillis()+"," +
                SongSheetColumns.subscribedCount+" int not null def 0," +
                SongSheetColumns.trackCount+" int not null def 0," +
                SongSheetColumns.coverImgUrl+" char not null def ''," +
                SongSheetColumns.description+" char def ''," +
                SongSheetColumns.tag+" char def ''," +
                SongSheetColumns.playCount+" int not null def 0," +
                SongSheetColumns.trackUpdateTime+" long def "+System.currentTimeMillis()+"," +
                SongSheetColumns.totalDuration+" long not null def 0," +
                SongSheetColumns.shareCount+" int not null def 0," +
                SongSheetColumns.commentCount+" int not null def 0);");
        sqLiteDatabase.execSQL("create table if not exists "+RecentStoreColumns.TABLE_NAME+"("+
                RecentStoreColumns.ID+" long not null," +
                RecentStoreColumns.TIME_PLAYED+" long not null);");
        sqLiteDatabase.execSQL("create table if not exists "+SearchHistoryColumns.TABLE_NAME+"(" +
                SearchHistoryColumns.SEARCHSTRING+" char not null," +
                SearchHistoryColumns.TIMESEARCHED+" text not null);");
        sqLiteDatabase.execSQL("create table if not exists "+DownloadFileStoreColumns.TABLE_NAME+"(" +
                DownloadFileStoreColumns.ID+" int not null primary key," +
                DownloadFileStoreColumns.TOTAL_SIZE+" int not null," +
                DownloadFileStoreColumns.FILE_LENGTH+" int not null," +
                DownloadFileStoreColumns.DIR+" text not null," +
                DownloadFileStoreColumns.FILE_NAME+" text not null," +
                DownloadFileStoreColumns.ARTIST_NAME+" text not null," +
                DownloadFileStoreColumns.DOWNSTATUS+" int not null," +
                DownloadFileStoreColumns.URL+" text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ SongSheetColumns.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists "+RecentStoreColumns.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists "+SearchHistoryColumns.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
