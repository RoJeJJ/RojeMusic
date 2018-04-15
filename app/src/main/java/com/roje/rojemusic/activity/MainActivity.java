package com.roje.rojemusic.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.PlayList;
import com.roje.rojemusic.fragment.DiscoverFragment;
import com.roje.rojemusic.fragment.FriendsFragment;
import com.roje.rojemusic.fragment.MusicFragment;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.main_viewpager)
    ViewPager main_viewpager;
    @BindView(R.id.bar_btn_music)
    ImageView musicBtn;
    @BindView(R.id.bar_btn_disc)
    ImageView discoverBtn;
    @BindView(R.id.bar_btn_friends)
    ImageView friendsBtn;
    @BindView(R.id.nav_view)
    ScrimInsetsFrameLayout sInFrame;
    private ArrayList<ImageView> tabs;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intToolbar();
        initDataAndViews();
    }

    private void intToolbar() {
       super.initToolbar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.actionbar_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initDataAndViews() {
        MusicFragment musicFragment = new MusicFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        FriendsFragment friendsFragment = new FriendsFragment();
        fragments = new ArrayList<>();
        fragments.add(musicFragment);
        fragments.add(discoverFragment);
        fragments.add(friendsFragment);
        musicBtn.setOnClickListener(this);
        discoverBtn.setOnClickListener(this);
        friendsBtn.setOnClickListener(this);
        discoverBtn.setSelected(true);
        tabs = new ArrayList<>();
        tabs.add(musicBtn);
        tabs.add(discoverBtn);
        tabs.add(friendsBtn);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        main_viewpager.setAdapter(adapter);
        main_viewpager.setCurrentItem(1,false);
        main_viewpager.setOffscreenPageLimit(3);
        main_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<tabs.size();i++){
                    if (position == i)
                        tabs.get(i).setSelected(true);
                    else
                        tabs.get(i).setSelected(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bar_btn_music:
                main_viewpager.setCurrentItem(0,true);
                break;
            case R.id.bar_btn_disc:
                main_viewpager.setCurrentItem(1,true);
                break;
            case R.id.bar_btn_friends:
                main_viewpager.setCurrentItem(2,true);
                break;
        }
    }
}
