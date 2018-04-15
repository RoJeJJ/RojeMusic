package com.roje.rojemusic.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bilibili.magicasakura.widgets.TintTextView;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.event.VideoResp;
import com.roje.rojemusic.bean.event.VideoUrl;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.DisplayUtil;
import com.roje.rojemusic.utils.LogUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * 由ijkplayer提供，用于播放视频，需要给他传入一个surfaceView
 */
public class VideoPlayerIJK extends RelativeLayout implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnBufferingUpdateListener,
            IMediaPlayer.OnCompletionListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener,
            IMediaPlayer.OnSeekCompleteListener {

    private IMediaPlayer mMediaPlayer = null;

    /**
     * 视频文件地址
     */
    private String mPath = "";
    private VideoResp.Video video;

    private SurfaceView surfaceView;

    private Context mContext;
    private ImageView cover;
    private ImageView play;
    private TintTextView playCount;
    private ImageView playDyn;
    private TextView time;
    private AnimationDrawable animDrawable;
    private Presenter presenter;
    private Disposable obDisposable;
    private CountDownTimer countDownTimer;
    private Observer<VideoUrl> ob = new Observer<VideoUrl>() {
        @Override
        public void onSubscribe(Disposable d) {
            obDisposable = d;
        }

        @Override
        public void onNext(VideoUrl s) {
           if (s.getUrls().size() > 0) {
               mPath = s.getUrls().get(0).getUrl();
               load();
           }
        }

        @Override
        public void onError(Throwable e) {
            obDisposable.dispose();
        }

        @Override
        public void onComplete() {
            obDisposable.dispose();
        }
    };
    private SimpleDateFormat ms = new SimpleDateFormat("mm:ss", Locale.getDefault());

    public VideoPlayerIJK(@NonNull Context context) {
        super(context);
        initVideoView(context);
    }

    public VideoPlayerIJK(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVideoView(context);
    }

    public VideoPlayerIJK(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        mContext = context;
        presenter = new PresenterImpl();
        //获取焦点，不知道有没有必要~。~
//        setFocusable(true);
        createSurfaceView();

        animDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.mv_dyn_anim);
        int colorRes = ContextCompat.getColor(context, android.R.color.white);
        cover = new ImageView(context);
        cover.setScaleType(ImageView.ScaleType.FIT_XY);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(cover, params);


        play = new ImageView(context);
        play.setImageResource(R.drawable.mv_btn_play_selector);
        play.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoad();
            }
        });
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(play, params);

        playCount = new TintTextView(context);
        playCount.setTextColor(colorRes);
        playCount.setTextSize(10);
        playCount.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.mv_play_count_icn_ps), null, null, null);
        playCount.setCompoundDrawablePadding(DisplayUtil.dp2px(context, 2));
        playCount.setCompoundDrawableTintList(colorRes, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.bottomMargin = DisplayUtil.dp2px(context,8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            params.setMarginStart(DisplayUtil.dp2px(context,8));
        }else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.leftMargin = DisplayUtil.dp2px(context,8);
        }
        addView(playCount, params);


        time = new TextView(context);
        time.setId(R.id.mv_duration_id);
        time.setTextSize(10);
        time.setTextColor(colorRes);
        time.setLineSpacing(0,0);
        time.setIncludeFontPadding(false);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.bottomMargin = DisplayUtil.dp2px(context,8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            params.setMarginEnd(DisplayUtil.dp2px(context,8));
        }else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.rightMargin = DisplayUtil.dp2px(context,8);
        }
        addView(time, params);

        playDyn = new ImageView(context);
        playDyn.setImageDrawable(animDrawable);
        playDyn.setPadding(0,0,0,DisplayUtil.sp2px(context,1));
        playDyn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.mv_duration_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.addRule(RelativeLayout.START_OF,R.id.mv_duration_id);
            params.setMarginEnd(DisplayUtil.dp2px(context,2));
        }else {
            params.addRule(RelativeLayout.LEFT_OF,R.id.mv_duration_id);
            params.rightMargin = DisplayUtil.dp2px(context,2);
        }
        addView(playDyn, params);
    }

    public void onLoad() {
        JsonObject object = new JsonObject();
        int resolution = 0;
        for (int i=0;i<video.getResolutions().size();i++){
            if (video.getResolutions().get(i).getResolution() > resolution)
                resolution = video.getResolutions().get(i).getResolution();
        }
        if (resolution != 0){
            object.addProperty("ids", "[\"" + video.getVideoId() + "\"]");
            object.addProperty("resolution",String.valueOf(resolution));
            presenter.mvAddr(object,ob);
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize * 9 / 16, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置视频地址。
     * 根据是否第一次播放视频，做不同的操作。
     *
     * @param path the path of the video.
     */
    public void setVideoPath(String path) {
        if (TextUtils.equals("", mPath)) {
            //如果是第一次播放视频，那就创建一个新的surfaceView
            mPath = path;
            createSurfaceView();
        } else {
            //否则就直接load
            mPath = path;
            load();
        }
    }

    public void setVideo(VideoResp.Video video) {
        this.video = video;
        int count = video.getPlayTime();
        if (count >= 100000)
            playCount.setText(mContext.getString(R.string.play_count, count / 10000));
        else
            playCount.setText(String.valueOf(count));
        time.setText(ms.format(new Date(video.getDurationms())));
        if (video.getCoverUrl() != null && !"".equals(video.getCoverUrl()))
            Glide.with(mContext).load(video.getCoverUrl())
                    .transition(withCrossFade())
                    .into(cover);
    }

    /**
     * 新建一个surfaceview
     */
    private void createSurfaceView() {
        //生成一个新的surface view
        surfaceView = new SurfaceView(mContext);
        surfaceView.getHolder().addCallback(new LmnSurfaceCallback());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT
                , LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(layoutParams);
        this.addView(surfaceView);
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        LogUtil.i(iMediaPlayer.getDuration()+"");
        cover.setVisibility(GONE);
        play.setVisibility(GONE);
        playCount.setVisibility(GONE);
        if (animDrawable != null)
            animDrawable.start();
        start(false);
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }

    /**
     * surfaceView的监听器
     */
    private class LmnSurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            //surfaceview创建成功后，加载视频
//            load();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    }

    /**
     * 加载视频
     */
    private void load() {
        //每次都要重新创建IMediaPlayer
        createPlayer();
        try {
            mMediaPlayer.setDataSource(mPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //给mediaPlayer设置视图
        mMediaPlayer.setDisplay(surfaceView.getHolder());

        mMediaPlayer.prepareAsync();
    }

    /**
     * 创建一个新的player
     */
    private void createPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.setDisplay(null);
            mMediaPlayer.release();
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        IjkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);

//开启硬解码        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);

        mMediaPlayer = ijkMediaPlayer;

            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnInfoListener(this);
            mMediaPlayer.setOnSeekCompleteListener(this);
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnErrorListener(this);

    }



    /**
     * -------======--------- 下面封装了一下控制视频的方法
     */

    public void start(boolean mute) {
        if (mMediaPlayer != null) {
            if (mute)
                mMediaPlayer.setVolume(0,0);
            mMediaPlayer.start();
            countTime(mMediaPlayer.getDuration() - mMediaPlayer.getCurrentPosition());
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }


    public void reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
        }
    }


    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }


    public long getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    public void seekTo(long l) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(l);
        }
    }
    private void countTime(long millInFuture){
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(millInFuture,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(ms.format(new Date(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                cover.setVisibility(VISIBLE);
                play.setVisibility(VISIBLE);
                playCount.setVisibility(VISIBLE);
                time.setText(ms.format(video.getDurationms()));
                if (animDrawable != null)
                    animDrawable.stop();
                release();
            }
        };
        countDownTimer.start();
    }
}