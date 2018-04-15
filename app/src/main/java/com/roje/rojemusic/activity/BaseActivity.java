package com.roje.rojemusic.activity;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.roje.rojemusic.utils.StatusBarUtil;

/**
 * activity 基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        StatusBarUtil.fixStatusBar(this);
    }

    public void initToolbar(Toolbar toolbar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                toolbar.setPadding(0, StatusBarUtil.getStatusBarHeight(this),0,0);
        setSupportActionBar(toolbar);
    }
}
