package com.roje.rojemusic.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.roje.rojemusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoadingDialogFragment extends AttachDialogFragment {
    @BindView(R.id.load)
    ProgressBar loading;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(activity,R.style.loadingDialog);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_SEARCH /*| keyCode == KeyEvent.KEYCODE_BACK*/;
            }
        });
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_loading,container,false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    private void initViews() {
    }
}
