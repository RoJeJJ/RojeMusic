package com.roje.rojemusic.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.holder.BaseHolder;
import com.roje.rojemusic.adapter.holder.PlayListHolder;
import com.roje.rojemusic.adapter.holder.MainItemHolder;
import com.roje.rojemusic.adapter.holder.ListTitleHolder;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.info.musiclist.BaseItem;
import com.roje.rojemusic.info.musiclist.MainItem;
import com.roje.rojemusic.info.musiclist.SheetTabItem;

import java.util.List;


public class MainMusicAdapter extends RecyclerView.Adapter<BaseHolder<? extends BaseItem>>  implements ListTitleHolder.OnExpandableListener{
    private static final int ITEM_TYPE_MAIN= 0;
    private static final int ITEM_TYPE_TITLE = 1;
    private static final int ITEM_TYPE_PLAYLIST= 2;
    private FragmentActivity activity;
    private List<BaseItem> mData;
    public MainMusicAdapter(FragmentActivity activity,List<BaseItem> list){
        this.activity = activity;
        mData = list;
    }
    @Override
    public BaseHolder<? extends BaseItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ITEM_TYPE_MAIN:
                view = LayoutInflater.from(activity).inflate(R.layout.music_main_item,parent,false);
                return new MainItemHolder<>(view, activity);
            case ITEM_TYPE_TITLE:
                view = LayoutInflater.from(activity).inflate(R.layout.playlist_title,parent,false);
                return new ListTitleHolder<>(view,activity,this);
            case ITEM_TYPE_PLAYLIST:
                view = LayoutInflater.from(activity).inflate(R.layout.main_music_playlist_item,parent,false);
                return new PlayListHolder<>(view,activity);
            default:
                view = LayoutInflater.from(activity).inflate(R.layout.music_main_item,parent,false);
                return new MainItemHolder<>(view,activity);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseHolder<? extends BaseItem> holder, int position) {
        switch (holder.getItemViewType()){
            case ITEM_TYPE_MAIN:
                MainItemHolder<MainItem> mainItemHolder = (MainItemHolder) holder;
                mainItemHolder.bindView((MainItem) mData.get(position),position);
                break;
            case ITEM_TYPE_TITLE:
                ListTitleHolder<SheetTabItem> listTitleHolder = (ListTitleHolder) holder;
                listTitleHolder.bindView((SheetTabItem) mData.get(position),position);
                break;
            case ITEM_TYPE_PLAYLIST:
                PlayListHolder<Playlist> playListHolder = (PlayListHolder<Playlist>) holder;
                playListHolder.bindView((Playlist) mData.get(position),position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public int getItemViewType(int position) {
        BaseItem item = mData.get(position);
        if (item instanceof MainItem)
            return ITEM_TYPE_MAIN;
        else if (item instanceof SheetTabItem)
            return ITEM_TYPE_TITLE;
        else if (item instanceof Playlist)
            return ITEM_TYPE_PLAYLIST;
        else
            return ITEM_TYPE_MAIN;
    }

    @Override
    public void onExpand(ListTitleHolder holder,SheetTabItem item) {
        if (item.getPlaylist().size() > 0){
            mData.addAll(holder.getAdapterPosition()+1,item.getPlaylist());
//            notifyItemRangeInserted(item.getPosition()+1,item.getPlayLists().size());
            notifyItemRangeInserted(holder.getAdapterPosition()+1,item.getPlaylist().size());
            notifyItemRangeChanged(holder.getAdapterPosition()+1,mData.size());
//            ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(item.getPosition(),0);
        }
    }

    @Override
    public void onHide(ListTitleHolder holder,SheetTabItem item) {
        if (item.getPlaylist().size() > 0){
            mData.removeAll(item.getPlaylist());
            notifyItemRangeRemoved(holder.getAdapterPosition()+1,item.getPlaylist().size());
            notifyItemRangeChanged(holder.getAdapterPosition()+1,mData.size());
        }
    }

    public void refresh(SheetTabItem item,List<Playlist> list) {
        if (item.isExpand()){
            mData.removeAll(item.getPlaylist());
            item.setPlaylists(list);
            mData.addAll(item.getPosition()+1,list);
        }else {
            item.setPlaylists(list);
        }
        notifyDataSetChanged();
    }
}
