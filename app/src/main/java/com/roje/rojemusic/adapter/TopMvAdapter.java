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
import com.roje.rojemusic.bean.topmv.MvBean;
import com.roje.rojemusic.utils.DisplayUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class TopMvAdapter extends RecyclerView.Adapter<TopMvAdapter.Holder> {
    private Context mContext;
    private List<MvBean> data;
    private int screenWidth;
    public TopMvAdapter(Context context, List<MvBean> list){
        this.mContext = context;
        this.data = list;
        screenWidth = DisplayUtil.screenWith(context);
    }
    @Override
    public TopMvAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mv_item_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(TopMvAdapter.Holder holder, int position) {
        MvBean bean = data.get(position);
        holder.title.setText(mContext.getString(R.string.na_na,bean.getName(),bean.getArtistName()));
        ViewGroup.LayoutParams params = holder.cover.getLayoutParams();
        params.width = screenWidth;
        params.height = (int) (screenWidth * 0.56f);
        holder.cover.setLayoutParams(params);
        if (bean.getPlayCount() >= 100000)
            holder.count.setText(mContext.getString(R.string.play_count,bean.getPlayCount()/10000));
        else
            holder.count.setText(String.valueOf(bean.getPlayCount()));
        Glide.with(mContext)
                .load(bean.getCover())
                .transition(withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                .into(holder.cover);
    }

    public void setData(List<MvBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public void addData(List<MvBean> list){
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageLayout)
        ImageView cover;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.play_count)
        TextView count;
        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
