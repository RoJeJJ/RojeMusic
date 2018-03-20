package com.roje.rojemusic.db;


import android.provider.MediaStore;

public interface SortOrder {
    public interface SongSortOrder{
        String SORT_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        String SORT_Z_A = SORT_A_Z+" DESC";
        String SORT_ARTIST = MediaStore.Audio.Media.ARTIST;
        String SORT_ALBUM = MediaStore.Audio.Media.ALBUM;
        String SORT_YEAR = MediaStore.Audio.Media.YEAR+" DESC";
        String SORT_DURATION = MediaStore.Audio.Media.DURATION+" DESC";
        String sort_date = MediaStore.Audio.Media.DATE_ADDED+" DESC";
        String SORT_FILENAME = MediaStore.Audio.Media.DATA;
    }
}
