package com.roje.rojemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.privatecontent.PriContResult;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PrivContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<PriContResult> data;
    private String title;

    public PrivContentAdapter(Context context, List<PriContResult> list, int titleRes) {
        this.mContext = context;
        this.data = list;
        this.title = context.getString(R.string.recomm_pc);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rec_item_head, parent, false);
            return new HeadHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item_rec_pc, parent, false);
            return new ItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder) {
            HeadHolder h = (HeadHolder) holder;
            h.title.setText(R.string.recomm_pc);
        } else if (holder instanceof ItemHolder) {
            PriContResult bean = data.get(position - 1);
            ItemHolder h = (ItemHolder) holder;
            h.name.setText(bean.getName());
            if (position == getItemCount() - 1)
                Glide.with(mContext).load(bean.getPicUrl())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                        .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                        .into(h.cover);
            else
                Glide.with(mContext).load(bean.getSPicUrl())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                        .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                        .into(h.cover);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        notifyItemChanged(0);
    }

    public void setData(List<PriContResult> list) {
        data.clear();
        data.addAll(list);
        Collections.reverse(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cover)
        ImageView cover;
        @BindView(R.id.name)
        TextView name;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
