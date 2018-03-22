package com.roje.rojemusic.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.roje.rojemusic.R;
import com.roje.rojemusic.activity.LoginActivity;
import com.roje.rojemusic.bean.detail.UserDetailBean;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.LogUtil;
import com.roje.rojemusic.utils.NetWorkUtil;
import com.roje.rojemusic.utils.SharedPreferencesUtil;

import java.security.MessageDigest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawerFragment extends BaseFragment {

    @BindView(R.id.topmenu_bgimg)
    ImageView bgImg;
    @BindView(R.id.topmenu_login)
    Button login;
    @BindView(R.id.topmenu_profile_layout)
    RelativeLayout profileLayout;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.level)
    TextView level;
    private Presenter presenter;
    private MyObserver<UserDetailBean> observer;
    private UserDetailBean detailBean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxInit();
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new MyObserver<UserDetailBean>(activity) {
            @Override
            protected void next(UserDetailBean s) {
                    detailBean = s;
                    refresh();
            }
        };
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    private void initViews() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        refresh();
    }
    private void refresh(){
        if (detailBean == null){
            login.setVisibility(View.VISIBLE);
            profileLayout.setVisibility(View.GONE);
            Glide.with(activity).load(R.drawable.topinfo_ban_bg).into(bgImg);
        }else {
            login.setVisibility(View.GONE);
            profileLayout.setVisibility(View.VISIBLE);
            level.setText(String.valueOf(detailBean.getLevel()));
            Glide.with(activity).load(detailBean.getProfile().getBackgroundUrl()).into(bgImg);
            Glide.with(activity).load(detailBean.getProfile().getAvatarUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleBitmapTransform())).into(avatar);
            nickname.setText(detailBean.getProfile().getNickname());
        }
    }
    class CircleBitmapTransform extends BitmapTransformation{

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            int width = toTransform.getWidth();
            int height = toTransform.getHeight();
            int min = Math.min(width,height);
            Bitmap result = pool.get(min,min, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(result);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            c.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            c.drawCircle(min/2,min/2,min/2,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Rect srcR;
            if (width >= height){
                srcR = new Rect((width-height)/2,0,(width+height)/2,height);
            }else {
                srcR = new Rect(0,(height - width)/2,width,(height+width)/2);
            }
            c.drawBitmap(toTransform,srcR, new Rect(0,0,min,min),paint);
            return result;
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }
    private void load(){
        long uid = SharedPreferencesUtil.getUid(activity);
        if (uid != -1) {
            if (NetWorkUtil.isNetWorkAvailable(activity)) {
                presenter.userDetail(uid, observer);
            }
        }
    }
}
