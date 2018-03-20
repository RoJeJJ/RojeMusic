package com.roje.rojemusic.fragment.discover;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roje.rojemusic.R;
import com.roje.rojemusic.fragment.BaseFragment;


public class DiscoverFMFragment extends BaseFragment {
    public DiscoverFMFragment(){}

    public static DiscoverFMFragment newInstance(){
        return new DiscoverFMFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.discover_fm_fragment,container,false);
        return root;
    }
}
