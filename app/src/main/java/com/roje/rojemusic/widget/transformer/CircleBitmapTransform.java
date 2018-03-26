package com.roje.rojemusic.widget.transformer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class CircleBitmapTransform extends BitmapTransformation {

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            int width = toTransform.getWidth();
            int height = toTransform.getHeight();
            int min = Math.min(width,height);
            Bitmap result = pool.get(min,min, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(result);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            c.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            c.drawCircle(min/2,min/2,min/2,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Rect srcR;
            if (width >= height){
                srcR = new Rect((width-height)/2,0,(width+height)/2,height);
            }else {
                srcR = new Rect(0,(height - width)/2,width,(height+width)/2);
            }
            c.drawBitmap(toTransform,srcR, new Rect(0,0,min,min),paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }