package com.roje.rojemusic.adapter.holder;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bilibili.magicasakura.widgets.TintImageView;
import com.roje.rojemusic.R;

import com.roje.rojemusic.activity.LocalMusicActivity;
import com.roje.rojemusic.info.musiclist.MainItem;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainItemHolder<T extends MainItem> extends BaseHolder<T> {
    @BindView(R.id.main_item_iv)
    TintImageView icn;
    @BindView(R.id.main_item_title)
    TextView title;
    @BindView(R.id.main_item_title_layout)
    public
    LinearLayout title_ll;
    @BindView(R.id.main_item_count)
    TextView count;
    @BindView(R.id.main_item_end_icn)
    ImageView set;
    private FragmentActivity activity;
    public MainItemHolder(View itemView, FragmentActivity activity) {
        super(itemView);
        this.activity = activity;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(final T data, int position) {
        data.setPosition(position);
        itemView.setBackgroundResource(R.drawable.song_sheet_title_bg);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getPosition() == 0)
                    activity.startActivity(new Intent(activity, LocalMusicActivity.class));
            }
        });
        icn.setImageResource(data.getIcn());
        title.setText(data.getName());
        count.setText(activity.getString(R.string.count_num,data.getCount()));
    }
}
