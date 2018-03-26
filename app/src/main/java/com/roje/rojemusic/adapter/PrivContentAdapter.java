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
import com.roje.rojemusic.bean.content.ContentRespBean;
import com.roje.rojemusic.utils.DisplayUtil;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class PrivContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ContentRespBean.ResultBean> data;
    private String title;
    private int screenWidth;

    public PrivContentAdapter(Context context, List<ContentRespBean.ResultBean> list, int titleRes) {
        this.mContext = context;
        this.data = list;
        this.title = context.getString(titleRes);
        screenWidth = DisplayUtil.screenWith(context);
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
            h.title.setText(title);
        } else if (holder instanceof ItemHolder) {
            ContentRespBean.ResultBean bean = data.get(position - 1);
            final ItemHolder h = (ItemHolder) holder;
            h.name.setText(bean.getName());
            if (position == getItemCount() - 1) {
                ViewGroup.LayoutParams params = h.cover.getLayoutParams();
                int height;
                float scale;
               if (bean.getWidth() != 0 && bean.getHeight() != 0){
                   scale = (bean.getHeight()*0.1f)/(bean.getWidth()*0.1f);
               }else {
                   scale =399f/1080f;
               }
               height = (int) (screenWidth*scale);
               params.width = screenWidth;
               params.height = height;
               h.cover.setLayoutParams(params);
                Glide.with(mContext)
                        .load(bean.getPicUrl())
                        .transition(withCrossFade())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                        .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                        .into(h.cover);
            } else {
                ViewGroup.LayoutParams params = h.cover.getLayoutParams();
                int height;
                float scale;
                if (bean.getWidth() != 0 && bean.getHeight() != 0){
                    scale = (bean.getHeight()*0.1f)/(bean.getWidth()*0.1f);
                }else {
                    scale =366f/640f;
                }
                int width = (screenWidth - 2) /2;
                height = (int) (width * scale);
                params.width = width;
                params.height = height;
                h.cover.setLayoutParams(params);
                Glide.with(mContext).load(bean.getSPicUrl())
                        .transition(withCrossFade())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                        .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                        .into(h.cover);
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
        notifyItemChanged(0);
    }

    public void setData(List<ContentRespBean.ResultBean> list) {
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
