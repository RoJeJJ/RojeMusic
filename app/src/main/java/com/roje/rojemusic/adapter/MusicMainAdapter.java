package com.roje.rojemusic.adapter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.activity.LocalMusicActivity;
import com.roje.rojemusic.db.store.SongSheetColumns;
import com.roje.rojemusic.entities.DialogItem;
import com.roje.rojemusic.entities.PlayList;
import com.roje.rojemusic.fragment.dialog.MoreFragment;
import com.roje.rojemusic.info.musiclist.BaseItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicMainAdapter extends RecyclerView.Adapter {
    private List<PlayList> createPlayList;
    private List<PlayList> collectPlayList;
    private boolean createExpandable;
    private boolean collectExpandable;
    private Activity activity;
//    private List<MainMusicInfo> mainMusicInfos;
    private List<Object> allItems;
    private MyAsyncQHandler qHanler;
    private Button commit;
    public MusicMainAdapter(Activity activity /*List<MainMusicInfo> mainMusicInfos*/, List<PlayList> list1, List<PlayList> list2){
        this.activity = activity;
//        this.mainMusicInfos = mainMusicInfos;
        allItems = new ArrayList<>();
//        allItems.addAll(mainMusicInfos);
        allItems.add(activity.getString(R.string.create_play_list_title));
        allItems.add(activity.getString(R.string.collect_play_list_title));
        createExpandable = true;
        collectExpandable = true;
        createPlayList = list1;
        collectPlayList = list2;
        qHanler = new MyAsyncQHandler(activity.getContentResolver());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(activity).inflate(R.layout.music_main_item,parent,false);
                return new MainItemHolder(view);
            case 1:
            case 2:
                view = LayoutInflater.from(activity).inflate(R.layout.sheet_tab,parent,false);
                return new MainItemTitleHolder(view);
            case 3:
                view = LayoutInflater.from(activity).inflate(R.layout.main_music_playlist_item,parent,false);
                return new SheetListItemHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
//                MainMusicInfo item = mainMusicInfos.get(position);
                final MainItemHolder mainItemHolder = (MainItemHolder) holder;
//                mainItemHolder.iv.setImageResource(item.getAvatar());
//                mainItemHolder.title.setText(item.getTitle());
//                mainItemHolder.count.setText(activity.getString(R.string.count_num,item.getCount()));
                mainItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (mainItemHolder.getAdapterPosition()){
                            case 0:
                                Intent intent = new Intent(activity, LocalMusicActivity.class);
                                activity.startActivity(intent);
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                        }
                    }
                });
                break;
            case 1:
                final MainItemTitleHolder holder1 = (MainItemTitleHolder) holder;
                holder1.titleText.setText(activity.getString(R.string.create_play_list_title,createPlayList.size()));
                holder1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(holder1.expandableIcn,"rotation",90,0);
                        anim.setDuration(200);
                        anim.setRepeatCount(0);
                        anim.setInterpolator(new LinearInterpolator());
                        if (createExpandable) {
                            allItems.removeAll(createPlayList);
                            notifyItemRangeRemoved(holder1.getAdapterPosition()+1,createPlayList.size());
//                            notifyDataSetChanged();
                            anim.start();
                            createExpandable = false;
                        }else {
                            for (int i=1;i<createPlayList.size()+1;i++)
                                allItems.add(holder1.getAdapterPosition()+i,createPlayList.get(i-1));
                            notifyItemRangeInserted(holder1.getAdapterPosition()+1,createPlayList.size());
//                            notifyDataSetChanged();
                            anim.reverse();
                            createExpandable = true;
                        }
                    }
                });
                holder1.itemSet.setOnClickListener(createItemSetClickListener);
                break;
            case 2:
                final MainItemTitleHolder holder2 = (MainItemTitleHolder) holder;
                holder2.titleText.setText(activity.getString(R.string.collect_play_list_title,collectPlayList.size()));
                holder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ObjectAnimator anim = ObjectAnimator.ofFloat(holder2.expandableIcn,"rotation",90,0);
                        anim.setDuration(200);
                        anim.setRepeatCount(0);
                        anim.setInterpolator(new LinearInterpolator());
                        if (collectExpandable){
                            // TODO: 2017/11/15
                            anim.start();
                            collectExpandable = false;
                        }else {
                            // TODO: 2017/11/15
                            anim.reverse();
                            collectExpandable = true;
                        }
                    }
                });
                break;
            case 3:
                PlayList playList = (PlayList) allItems.get(position);
                SheetListItemHolder itemHolder = (SheetListItemHolder) holder;
                itemHolder.title.setText(playList.getName());
                itemHolder.count.setText(activity.getString(R.string.pl_count,playList.getSongCount()));
                if (playList.getId() == 0){
                    if (playList.getAlbumArt() == null || "".equals(playList.getAlbumArt())){
                        itemHolder.cover.setImageResource(R.drawable.love_playlist_mask);
                    }else {
                    }
                }else {
                    if (playList.getAlbumArt() == null || "".equals(playList.getAlbumArt())){
                        itemHolder.cover.setImageResource(R.drawable.default_playlist_cover);
                    }else {

                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (allItems.get(position) instanceof BaseItem)
            return 0;
        else if (allItems.get(position) instanceof PlayList )
            return 3;
        else if (position == 5 && allItems.get(position) instanceof String)
            return 1;
        else
            return 2;
    }
    public class MainItemHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.main_item_iv)
        ImageView iv;
        @BindView(R.id.main_item_title)
        TextView title;
        @BindView(R.id.main_item_count)
        TextView count;
        @BindView(R.id.main_item_end_icn)
        ImageView endIcn;
        @BindView(R.id.main_item_title_layout)
        public LinearLayout titlelayout;
        MainItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setBackgroundResource(R.drawable.list_item_click_bg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    class MainItemTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sheet_tab_expandable_icn)
        ImageView expandableIcn;
        @BindView(R.id.sheet_tab_text)
        TextView titleText;
        @BindView(R.id.sheet_tab_title_set)
        ImageView itemSet;
        MainItemTitleHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundResource(R.drawable.list_item_title_bg);
            ButterKnife.bind(this,itemView);
        }
    }
    public class SheetListItemHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.common_item_cover)
        ImageView cover;
//        @BindView(R.id.common_item_title)
        TextView title;
//        @BindView(R.id.common_item_count)
        TextView count;
        SheetListItemHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
        }
    }


    public void updateCreate(List<PlayList> list){
        if (createExpandable){
            allItems.removeAll(createPlayList);
            for (int i=1;i<list.size()+1;i++)
                allItems.add(5+i,list.get(i-1));
        }
        createPlayList = list;
        notifyItemChanged(5);
        notifyItemChanged(6,list.size());
    }
    private View.OnClickListener createItemSetClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title = activity.getString(R.string.create_dialog_title);
            ArrayList<DialogItem> items = new ArrayList<>();
            items.add(new DialogItem(R.drawable.lay_icn_fav,activity.getString(R.string.new_sheet)));
            items.add(new DialogItem(R.drawable.lay_icn_edit,activity.getString(R.string.pl_manage)));
//            for (int i=0;i<20;i++){
//                items.add(new DialogItem(R.drawable.lay_icn_fav,R.string.pl_manage));
//            }
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
                        initViewsForAddDialog(root,dialog);
                        dialog.show();
                    }
                }
            });
            fragment.show(((AppCompatActivity)activity).getSupportFragmentManager(),MoreFragment.TAG);
//
        }
    };


    private void initViewsForAddDialog(View root, final Dialog dialog) {
        final EditText name = ButterKnife.findById(root,R.id.new_playlist_name);
        final TextView count = ButterKnife.findById(root,R.id.count_hint);
        Button cancel = ButterKnife.findById(root,R.id.cancel_btn);
        commit = ButterKnife.findById(root,R.id.commit_btn);
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
//                qHanler.startInsert(0,null, RoJeMusicProvider.PLAYLIST_URI,cv);
                dialog.dismiss();
            }
        });
    }
    static class MyAsyncQHandler extends AsyncQueryHandler{

        MyAsyncQHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            Log.i("urixxxxx",uri.toString());
        }
    }
}
