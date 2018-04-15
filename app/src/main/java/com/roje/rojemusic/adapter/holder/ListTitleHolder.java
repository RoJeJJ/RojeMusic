package com.roje.rojemusic.adapter.holder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.async.AsyncHandlerHelper;
import com.roje.rojemusic.db.store.SongSheetColumns;
import com.roje.rojemusic.entities.DialogItem;
import com.roje.rojemusic.fragment.dialog.MoreFragment;
import com.roje.rojemusic.info.musiclist.SheetTabItem;
import com.roje.rojemusic.provider.RoJeMusicProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTitleHolder<T extends SheetTabItem> extends BaseHolder<T> {
    public interface OnExpandableListener {
        void onExpand(ListTitleHolder holder,SheetTabItem item);
        void onHide(ListTitleHolder holder,SheetTabItem item);
    }
    @BindView(R.id.title_ll)
    LinearLayout title_ll;
    @BindView(R.id.expand_icn)
    ImageView iv;
    @BindView(R.id.title_text)
    TextView title;
    @BindView(R.id.title_set)
    ImageView title_set;
    private OnExpandableListener listener;
    private FragmentActivity activity;
    public ListTitleHolder(View itemView, FragmentActivity activity, OnExpandableListener listener) {
        super(itemView);
        this.activity = activity;
        this.listener = listener;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(final T itemData, int position) {
        itemData.setPosition(position);
        itemView.setBackgroundResource(R.drawable.sheet_tab_bg);
        iv.setImageResource(R.drawable.recommend_icn_arr);
        if (itemData.isExpand())
            iv.setRotation(90);
        title_set.setImageResource(R.drawable.local_scan_icn_set);
        if (itemData.getType() == SheetTabItem.Type.title_type_create) {
            title.setText(activity.getString(R.string.create_play_list_title, itemData.getPlaylist().size()));
            title_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialogMyPlayList();
                }
            });

        }else if (itemData.getType() == SheetTabItem.Type.title_type_collect){
            itemView.setBackgroundResource(R.drawable.sheet_tab_bg);
            title.setText(activity.getString(R.string.collect_play_list_title, itemData.getPlaylist().size()));
            title_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemData.isExpand()){
                    itemData.setExpand(true);
                    listener.onExpand(ListTitleHolder.this,itemData);
                    rotationAnimation(true);
                }else {
                    itemData.setExpand(false);
                    listener.onHide(ListTitleHolder.this,itemData);
                    rotationAnimation(false);
                }
            }
        });
    }

    private void rotationAnimation(boolean expand){
        Animation animation;
        if (expand)
            animation = AnimationUtils.loadAnimation(activity,R.anim.pl_arr_drop);
        else
            animation = AnimationUtils.loadAnimation(activity,R.anim.pl_arr_raise);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }


    private void createDialogMyPlayList(){
        String title = activity.getString(R.string.create_dialog_title);
        ArrayList<DialogItem> items = new ArrayList<>();
        items.add(new DialogItem(R.drawable.lay_icn_fav,activity.getString(R.string.new_sheet)));
        items.add(new DialogItem(R.drawable.lay_icn_edit,activity.getString(R.string.pl_manage)));

        final MoreFragment fragment = MoreFragment.newInstance(title,items);
        fragment.setDialogItemClickListener(new MoreFragment.DialogItemClickListener() {
            @Override
            public void itemClick(int position) {
                fragment.dismiss();
                if (position == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    @SuppressLint("InflateParams")
                    View root = LayoutInflater.from(activity).inflate(R.layout.add_playlist_dialog_layout,null);
                    builder.setView(root);
                    Dialog dialog = builder.create();
                    initViewsCreateDialog(root,dialog);
                    dialog.show();
                }
            }
        });
        fragment.show(activity.getSupportFragmentManager(),MoreFragment.TAG);
    }

    private void initViewsCreateDialog(View root, final Dialog dialog) {
        final EditText name = ButterKnife.findById(root,R.id.new_playlist_name);
        final TextView count = ButterKnife.findById(root,R.id.count_hint);
        Button cancel = ButterKnife.findById(root,R.id.cancel_btn);
        final Button commit = ButterKnife.findById(root,R.id.commit_btn);
        count.setText(activity.getString(R.string.newSheet_char_count,0));
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    byte[] b = editable.toString().getBytes("gbk");
                    count.setText(activity.getString(R.string.newSheet_char_count,b.length));
                    if (b.length != 0 && b.length <=40 ) {
                        if (!commit.isEnabled())
                            commit.setEnabled(true);
                    }else{
                        if (commit.isEnabled())
                            commit.setEnabled(false);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put(SongSheetColumns.id,(long)name.getText().hashCode());
                cv.put(SongSheetColumns.name,name.getText().toString());
                cv.put(SongSheetColumns.userId,0);
                cv.put(SongSheetColumns.trackCount,0);
                cv.put(SongSheetColumns.createTime,System.currentTimeMillis());
                cv.put(SongSheetColumns.updateTime,System.currentTimeMillis());
                new AsyncHandlerHelper(activity).startInsert(0,null, RoJeMusicProvider.SONG_SHEET_URI,cv);
                dialog.dismiss();
            }
        });
    }
}
