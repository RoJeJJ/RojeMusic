package com.roje.rojemusic.db.store;


public interface DownloadFileStoreColumns {
        /* Table name */
        String TABLE_NAME = "downfile_info";

        /* Album IDs column */
        String ID = "id";

        /* Time played column */
        String TOTAL_SIZE = "totalsize";

        String FILE_LENGTH = "complete_length";

        String URL = "url";

        String DIR = "dir";
        String FILE_NAME = "file_name";
        String ARTIST_NAME = "artist";
        String DOWNSTATUS = "notification_type";
}
