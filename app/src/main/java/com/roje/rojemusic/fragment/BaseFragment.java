package com.roje.rojemusic.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


public class BaseFragment extends Fragment {
    protected FragmentActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }
}
