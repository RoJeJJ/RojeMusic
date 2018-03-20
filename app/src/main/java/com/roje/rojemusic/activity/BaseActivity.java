package com.roje.rojemusic.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roje.rojemusic.utils.StatusBarUtil;

/**
 * activity 基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.fixStatusBar(this);
    }
}
