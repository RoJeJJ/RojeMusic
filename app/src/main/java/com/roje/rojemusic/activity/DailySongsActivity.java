package com.roje.rojemusic.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.daily_song.RecDailySongRespBean;
import com.roje.rojemusic.entities.DialogItem;
import com.roje.rojemusic.fragment.dialog.MoreFragment;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.NetWorkUtil;
import com.roje.rojemusic.utils.StatusBarUtil;
import com.roje.rojemusic.widget.view.LoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DailySongsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    TintToolbar toolbar;
    @BindView(R.id.dailyRecycle)
    RecyclerView dailyRecycle;
    @BindView(R.id.load)
    LoadLayout load;
    private DailySongAdapter adapter;
    private Presenter presenter;
    private List<RecDailySongRespBean.RecommendBean> dailySong;
    private Observer<List<RecDailySongRespBean.RecommendBean>> dailySongObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_songs);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        initViews();
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            toolbar.setPadding(0, StatusBarUtil.getStatusBarHeight(this),0,0);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        }
    }

    private void initViews() {
        load.setNwl(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
        dailyRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Drawable divider = ContextCompat.getDrawable(this,R.drawable.divider);
        if (divider != null){
            DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            itemDecoration.setDrawable(divider);
            dailyRecycle.addItemDecoration(itemDecoration);
        }
        dailyRecycle.setAdapter(adapter = new DailySongAdapter(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void initData() {
        dailySong = new ArrayList<>();
        presenter = new PresenterImpl();
        dailySongObserver = new Observer<List<RecDailySongRespBean.RecommendBean>>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
                load.setloading();
            }

            @Override
            public void onNext(List<RecDailySongRespBean.RecommendBean> recommendBeans) {
                dailySong.clear();
                adapter.notifyDataSetChanged();
                dailySong.addAll(recommendBeans);
                adapter.notifyDataSetChanged();
                load.loadSucceed();
            }

            @Override
            public void onError(Throwable e) {
                load.setLoadFail();
            }

            @Override
            public void onComplete() {
                d.dispose();
            }
        };
    }
    private void request(){
        if (!NetWorkUtil.isNetWorkAvailable(this)){
            load.setNoNetWork();
            return;
        }
        JsonObject o = new JsonObject();
        o.addProperty("offset",0);
        o.addProperty("total",true);
        o.addProperty("limit",20);
        presenter.recommendSong(o, dailySongObserver);
    }
    class DailySongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private Context context;
        DailySongAdapter(Context context){
            this.context = context;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0){
                View view = LayoutInflater.from(context).inflate(R.layout.daily_banner_item,parent,false);
                return new BannerHolder(view);
            }else if (viewType == 1){
                View view = LayoutInflater.from(context).inflate(R.layout.daily_item_head,parent,false);
                return new HeadHolder(view);
            }else if (viewType == 2){
                View view = LayoutInflater.from(context).inflate(R.layout.daily_song_item,parent,false);
                return new ItemHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof BannerHolder){
                BannerHolder h = (BannerHolder) holder;
            }else if (holder instanceof HeadHolder){
                HeadHolder h = (HeadHolder) holder;
            }else if (holder instanceof ItemHolder){
                ItemHolder h = (ItemHolder) holder;
                position -= 2;
                final RecDailySongRespBean.RecommendBean bean = dailySong.get(position);
                Glide.with(context).load(bean.getAlbum().getPicUrl())
                        .transition(withCrossFade())
                        .into(h.cover);
                h.title.setText(bean.getName());
                StringBuilder sb = new StringBuilder();
                for (int i=0;i<bean.getArtists().size();i++){
                    RecDailySongRespBean.RecommendBean.ArtistsBeanX artist = bean.getArtists().get(i);
                    sb.append(artist.getName());
                    if (i != bean.getArtists().size() - 1)
                        sb.append("/");
                }
                final String artists = new String(sb);
                sb.append(" - ").append(bean.getAlbum().getName());
                h.subtitle.setText(sb);
                h.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<DialogItem> items = new ArrayList<>();
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_next_play)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_collect)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_dld)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_comment)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_share)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_artist,artists)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_album,bean.getAlbum().getName())));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_video)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_ringtone)));
                        items.add(new DialogItem(R.drawable.lay_icn_edit,getString(R.string.daily_more_boring)));
                        MoreFragment.newInstance("歌曲",items).show(getSupportFragmentManager(),"null");
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (dailySong.size() == 0)
            return 1;
            else
                return 2 + dailySong.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 0;
            else if (position == 1){
                return 1;
            }else
                return 2;
        }
        class BannerHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.bg)
            ImageView bg;
            BannerHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
        class HeadHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.select_more)
            TextView choiceMore;
            HeadHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
        class ItemHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.cover)
            ImageView cover;
            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.subtitle)
            TextView subtitle;
            @BindView(R.id.sq_img)
            ImageView sqImg;
            @BindView(R.id.more)
            ImageView more;
            public ItemHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
