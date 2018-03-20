package com.roje.rojemusic.adapter.holder;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.PlayList;
import com.roje.rojemusic.bean.playlist.Playlist;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlayListHolder<T extends Playlist> extends BaseHolder<T> {
    @BindView(R.id.pl_cover)
    ImageView cover;
    @BindView(R.id.title_ll)
    public LinearLayout title_ll;
    @BindView(R.id.pl_title)
    TextView title;
    @BindView(R.id.pl_count)
    TextView count;
    @BindView(R.id.set)
    ImageView set;
    private FragmentActivity activity;
    public PlayListHolder(View itemView, FragmentActivity activity) {
        super(itemView);
        this.activity = activity;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(final T itemData, int position) {
        Glide.with(activity).load(itemData.getCoverImgUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.default_playlist_cover)).into(cover);
        title.setText(itemData.getName());
        count.setText(activity.getString(R.string.pl_count,itemData.getTrackCount()));
        itemView.setBackgroundResource(R.drawable.song_sheet_title_bg);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


}
