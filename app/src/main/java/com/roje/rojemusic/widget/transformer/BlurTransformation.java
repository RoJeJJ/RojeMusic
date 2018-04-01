package com.roje.rojemusic.widget.transformer;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.roje.rojemusic.utils.DisplayUtil;

import java.security.MessageDigest;

public class BlurTransformation extends BitmapTransformation{
    private int radius;
    private float scale;
    private Context mContext;

    /**
     *
     * @param context 上下文
     * @param radius 0<radius<=25
     * @param scale 缩放比例
     */
    public BlurTransformation(Context context,int radius,float scale){
        this.radius = radius;
        this.scale = scale;
        this.mContext = context;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = Math.round(toTransform.getWidth() * scale);
        int height = Math.round(toTransform.getHeight() * scale);

        int fixWith;
        int fixHeight;
        fixWith = DisplayUtil.screenWith(mContext)*height/DisplayUtil.screenHeight(mContext);
        if (fixWith <= width)
            fixHeight = height;
        else {
            fixWith = width;
            fixHeight = DisplayUtil.screenHeight(mContext) * width / DisplayUtil.screenWith(mContext);
        }

        Bitmap inputBmp = Bitmap.createBitmap(fixWith,fixHeight, Bitmap.Config.ARGB_8888);
        Bitmap bmp = Bitmap.createScaledBitmap(toTransform,width,height,false);

        Canvas canvas = new Canvas(inputBmp);
        ColorMatrix cm = new ColorMatrix();
        cm.setScale(0.5f,0.5f,0.5f,1f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        Rect src;
        if (fixWith < width)
            src = new Rect((width-fixWith)/2,0,(width-fixWith)/2+fixWith,fixHeight);
        else
            src = new Rect(0,(height-fixHeight)/2,fixWith,(height-fixHeight)/2+fixHeight);
        Rect dst = new Rect(0,0,fixWith,fixHeight);
            canvas.drawBitmap(bmp,src,dst,paint);
        bmp.recycle();
        RenderScript renderScript =  RenderScript.create(mContext);


        // Allocate memory for Renderscript to work with

        final Allocation input = Allocation.createFromBitmap(renderScript,inputBmp);
        final Allocation output = Allocation.createTyped(renderScript,input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);

        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);


        renderScript.destroy();
        return inputBmp;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}
