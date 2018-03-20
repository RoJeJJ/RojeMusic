package com.roje.rojemusic.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.recommand.Result;
import com.roje.rojemusic.ui.view.SquareImageView;


import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommPlayListAdapter extends RecyclerView.Adapter<CommPlayListAdapter.Holder> {
    private Context mContext;
    private List<Result> listBeans;
    public CommPlayListAdapter(Context context, List<Result> beans){
        this.mContext = context;
        this.listBeans = beans;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (listBeans.size() - 1 >= position){
            Result bean = listBeans.get(position);
            holder.name.setVisibility(View.VISIBLE);
            holder.name.setText(bean.getName());
            Glide.with(mContext).load(bean.getPicUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_150)).into(holder.cover);
//            holder.count.setCompoundDrawables(ContextCompat.getDrawable(mContext,R.drawable.index_icn_earphone),null,null,null);
            if (bean.getPlayCount() >= 10000)
                holder.count.setText(String.format(Locale.getDefault(),"%d万",(int)bean.getPlayCount()/10000));
            else
                holder.count.setText(String.valueOf((int) bean.getPlayCount()));
        }else
            holder.name.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        if (listBeans.size() > 6)
            return listBeans.size();
        else
            return 6;
    }
    class Holder extends RecyclerView.ViewHolder{

        @BindView(R.id.cover)
        SquareImageView cover;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.play_count)
         TextView count;
        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
