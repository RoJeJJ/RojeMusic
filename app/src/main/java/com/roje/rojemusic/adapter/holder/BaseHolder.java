package com.roje.rojemusic.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.roje.rojemusic.info.musiclist.BaseItem;


public abstract  class BaseHolder<T extends BaseItem> extends RecyclerView.ViewHolder {
    BaseHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindView(T itemData,int position);
}