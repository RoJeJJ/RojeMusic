package com.roje.rojemusic.widget.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.utils.DisplayUtil;


public class LoadLayout extends FrameLayout {
    private TextView load;
    private OnClickListener nwl;
    private AnimationDrawable aniDraw;
    public LoadLayout(@NonNull Context context) {
        this(context,null);
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public void setNwl(OnClickListener nwl) {
        this.nwl = nwl;
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        load = new TextView(context);
        load.setCompoundDrawablePadding(DisplayUtil.dp2px(context,4));
        load.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);
        aniDraw = (AnimationDrawable) ContextCompat.getDrawable(context,R.drawable.load_anim);
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(load,params);
    }

    public void loadSucceed(){
        setVisibility(GONE);
    }
    public void setNoNetWork(){
        load.setOnClickListener(nwl);
        load.setCompoundDrawables(null,null,null,null);
        load.setText(R.string.no_network);
        load.setBackgroundResource(R.drawable.round_btn_bg);
    }
    public void setloading(){
        load.setOnClickListener(null);
        load.setText(R.string.loading);
        if (aniDraw != null){
            aniDraw.setBounds(0,0,aniDraw.getIntrinsicWidth(),aniDraw.getIntrinsicHeight());
            load.setCompoundDrawables(aniDraw,null,null,null);
            aniDraw.start();
        }
    }
    public void setLoadFail(){
        load.setOnClickListener(nwl);
        load.setCompoundDrawables(null,null,null,null);
        load.setText(R.string.load_fail);
    }
}
