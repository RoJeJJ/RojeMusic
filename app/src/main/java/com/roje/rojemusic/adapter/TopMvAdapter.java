package com.roje.rojemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.roje.rojemusic.bean.topmv.MvBean;

import java.util.List;


public class TopMvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MvBean> data;
    public TopMvAdapter(Context context, List<MvBean> list){
        this.mContext = context;
        this.data = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
