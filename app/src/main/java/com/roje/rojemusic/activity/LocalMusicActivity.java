package com.roje.rojemusic.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.CommonFragmentAdapter;
import com.roje.rojemusic.fragment.tab.AlbumFragment;
import com.roje.rojemusic.fragment.tab.ArtistFragment;
import com.roje.rojemusic.fragment.tab.FolderFragment;
import com.roje.rojemusic.fragment.tab.SongFragment;
import com.roje.rojemusic.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalMusicActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        ButterKnife.bind(this);
        titles = getResources().getStringArray(R.array.tabTitles);
        initToolbar();
        initTab();
    }

    private void initTab() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SongFragment.newInstance());
        fragments.add(ArtistFragment.newInstance());
        fragments.add(AlbumFragment.newInstance());
        fragments.add(FolderFragment.newInstance());

        vp.setAdapter(new CommonFragmentAdapter(getSupportFragmentManager(), fragments,titles));
        tabLayout.setupWithViewPager(vp);
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            toolbar.setPadding(0, StatusBarUtil.getStatusBarHeight(this),0,0);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.setDisplayShowTitleEnabled(false);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
