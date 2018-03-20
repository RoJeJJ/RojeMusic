package com.roje.rojemusic.fragment.dialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;


public class AttachDialogFragment extends DialogFragment {
    protected FragmentActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

}
