package com.roje.rojemusic.async;


import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.net.Uri;
import android.util.Log;

public class AsyncHandlerHelper extends AsyncQueryHandler{

        public AsyncHandlerHelper(Activity activity) {
            super(activity.getContentResolver());
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            Log.i("urixxxxx",uri.toString());
        }
}
