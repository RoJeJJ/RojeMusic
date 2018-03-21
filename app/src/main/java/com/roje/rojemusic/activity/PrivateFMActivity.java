package com.roje.rojemusic.activity;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.roje.rojemusic.R;
import com.roje.rojemusic.async.BlurTransformation;
import com.roje.rojemusic.bean.personfm.Artist;
import com.roje.rojemusic.bean.personfm.Data;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivateFMActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.artist)
    TextView artist;
    @BindView(R.id.rightDurText)
    TextView rightText;
    @BindView(R.id.play_next)
    ImageView next;
    @BindView(R.id.imgSwitcher)
    ImageSwitcher ims;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    private Presenter presenter;
    private List<Data> fmSongs;
    private boolean in = false;
    private BlurTransformation blurTransformation;
    private MyObserver<List<Data>> observer;

    private SimpleTarget<Drawable> sTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{iv_bg.getDrawable(),resource});
            iv_bg.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(200);
        }

//        @Override
//        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Drawable> transition) {
//            Bitmap lastBmp = blurTransformation.getLastBmp();
//            if (lastBmp == null || lastBmp.isRecycled()){
//                lastBmp = BitmapFactory.decodeResource(getResources(),R.drawable.fm_bg);
//            }
//            BitmapDrawable bd = new BitmapDrawable(getResources(),resource);
//            TransitionDrawable drawable = new TransitionDrawable(new Drawable[]{iv_bg.getDrawable(),bd});
//            iv_bg.setImageDrawable(drawable);
//            drawable.startTransition(400);
//        }
    };

    private void refreshBoard(){
        if (fmSongs.size() > 0) {
            Data song = fmSongs.get(0);
            title.setText(song.getName());
            Iterator<Artist> iterator = song.getArtists().iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                Artist a = iterator.next();
                sb.append(a.getName());
                if (iterator.hasNext())
                    sb.append("&");
                else
                    break;
            }
            artist.setText(sb);
            title.setVisibility(View.VISIBLE);
            artist.setVisibility(View.VISIBLE);
            SimpleDateFormat format = new SimpleDateFormat("mm:ss",Locale.getDefault());
            rightText.setText(format.format(new Date(song.getDuration())));
//            Glide.with(PrivateFMActivity.this).clear(sTarget);
        }
        if (fmSongs.size() > 1){
            Data song = fmSongs.get(1);
            Glide.with(PrivateFMActivity.this).load(song.getAlbum().getPicUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_play_fm))
                    .apply(RequestOptions.skipMemoryCacheOf(true))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into((ImageView) ims.getNextView());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fm_layout);
        ButterKnife.bind(this);
        rxInit();
        initToolbar();
        initData();
        initViews();
        presenter.getPersonalFM(observer);
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new MyObserver<List<Data>>(this) {
            @Override
            protected void next(List<Data> data) {
                fmSongs.addAll(data);
                if (!in && fmSongs.size() > 0) {
                    in = true;
                    Glide.with(PrivateFMActivity.this).load(fmSongs.get(0).getAlbum().getPicUrl()).into((ImageView) ims.getCurrentView());
                    refreshBoard();
                    handler.sendEmptyMessage(0);
                }
            }
        };
    }

    private void initData() {
        blurTransformation = new BlurTransformation(this,25,0.25f);
        fmSongs = new ArrayList<>();
    }

    private long lastClick = 0L;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
           switch (message.what){
               case 0:
                   if (fmSongs.size() > 0) {
                       Data song = fmSongs.get(0);
                       Glide.with(PrivateFMActivity.this).load(song.getAlbum().getBlurPicUrl())
                               .apply(RequestOptions.bitmapTransform(blurTransformation))
                               .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                               .into(sTarget);
                   }
                   break;
               case 1:
                   break;
           }
            return true;
        }
    });
    private void initViews() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fmSongs.size() <= 1) {
                    Toast.makeText(PrivateFMActivity.this, "请稍后再试!", Toast.LENGTH_SHORT).show();
                } else {
                    fmSongs.remove(0);
                    ims.showNext();
                    if (System.currentTimeMillis() - lastClick < 200) {
                        handler.removeMessages(0);
                    }
                    lastClick = System.currentTimeMillis();
                    handler.sendEmptyMessageDelayed(0,200);
                    refreshBoard();
                }
                if (fmSongs.size() <= 2)
                    presenter.getPersonalFM(observer);
            }
        });
        ims.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(PrivateFMActivity.this);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
        ims.setImageResource(R.drawable.placeholder_disk_play_fm);
        iv_bg.setImageResource(R.drawable.fm_bg);

    }


    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            toolbar.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.actionbar_back);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}
