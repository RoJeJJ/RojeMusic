package com.roje.rojemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.recommand.RecPlResult;
import com.roje.rojemusic.ui.view.SquareImageView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecommendPlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<RecPlResult> listBeans;
    public RecommendPlAdapter(Context context, List<RecPlResult> beans){
        this.mContext = context;
        this.listBeans = beans;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0){
            View view = LayoutInflater.from(mContext).inflate(R.layout.rec_item_head,parent,false);
            return new HeadHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item_rec_pl, parent, false);
            return new ItemHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder){
            HeadHolder h = (HeadHolder) holder;
            h.title.setText(R.string.recomm_pl);
        }else {
            RecPlResult bean = listBeans.get(position - 1);
            ItemHolder h = (ItemHolder) holder;
            Glide.with(mContext).load(bean.getPicUrl())
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_150))
                    .into(h.cover);
            h.name.setText(bean.getName());
            if (bean.getPlayCount() >= 10000)
                h.count.setText(mContext.getString(R.string.play_count,(int)bean.getPlayCount()/10000));
            else
                h.count.setText(String.valueOf(bean.getPlayCount()));
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size()+1;
    }
    class ItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cover)
        ImageView cover;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.play_count)
         TextView count;
        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class HeadHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        TextView title;
        HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
