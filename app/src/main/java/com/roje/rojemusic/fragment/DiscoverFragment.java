package com.roje.rojemusic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.CommonFragmentAdapter;
import com.roje.rojemusic.fragment.discover.DiscoverFMFragment;
import com.roje.rojemusic.fragment.discover.DiscoverMusicFragment;
import com.roje.rojemusic.fragment.discover.DiscoverVideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    private String[] titles;
    private List<Fragment> fragments;
    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = getResources().getStringArray(R.array.dis_tab_titles);
        fragments = new ArrayList<>();
        fragments.add(DiscoverMusicFragment.newInstance());
        fragments.add(DiscoverVideoFragment.newInstance());
        fragments.add(DiscoverFMFragment.newInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.discover_fragment_layout, container, false);
        ButterKnife.bind(this,root);
        initView();
        return root;
    }

    private void initView() {
        vp.setAdapter(new CommonFragmentAdapter(getFragmentManager(),fragments,titles));
        tablayout.setupWithViewPager(vp);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //更新数据
        }
    }
}
