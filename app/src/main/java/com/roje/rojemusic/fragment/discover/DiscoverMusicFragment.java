package com.roje.rojemusic.fragment.discover;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.activity.PrivateFMActivity;
import com.roje.rojemusic.adapter.CommPlayListAdapter;
import com.roje.rojemusic.bean.Banner;
import com.roje.rojemusic.bean.recommand.Result;
import com.roje.rojemusic.fragment.BaseFragment;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class DiscoverMusicFragment extends BaseFragment {

    @BindView(R.id.ad_cover_vp)
    ViewPager vp;
    @BindView(R.id.llDots)
    LinearLayout llDots;
    @BindView(R.id.date_today)
    TextView daily_today;
    @BindView(R.id.iv_fm_btn)
    ImageView iv_fm;
    @BindView(R.id.iv_daily)
    ImageView iv_daily;
    @BindView(R.id.iv_upbill)
    ImageView iv_upbill;
    @BindView(R.id.recomm_recy)
    RecyclerView pl_recy;
    private List<ImageView> dots;
    private Presenter presenter;
    private List<Result> plBeans;
    private CommPlayListAdapter plAdapter;
    private LoopImageAdapter adapter;
    public DiscoverMusicFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    public static DiscoverMusicFragment newInstance(){
        return new DiscoverMusicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(activity).inflate(R.layout.discover_music_fragment,container,false);
        ButterKnife.bind(this,view);
        initData();
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        load();
    }

    private void load() {
        JsonObject object = new JsonObject();
        object.addProperty("limit",6);
        object.addProperty("offset",0);
        object.addProperty("total",true);
        object.addProperty("n",1000);
        presenter.getRecommendRes(object,new MyObserver<List<Result>>() {
            @Override
            protected void next(List<Result> results) {
                if (results != null){
                    plBeans.clear();
                    plBeans.addAll(results);
                    plAdapter.notifyDataSetChanged();
                }
            }
        });
        presenter.getBanners(new MyObserver<List<Banner>>() {
            @Override
            protected void next(List<Banner> banners) {
                List<View> views = new ArrayList<>();
                for (Banner banner:banners){
                    RelativeLayout layout = new RelativeLayout(activity);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ImageView iv = new ImageView(activity);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(activity).load(banner.getPic())
                            .apply(RequestOptions.placeholderOf(R.drawable.placeholder_disk_321))
                            .into(iv);
                    layout.addView(iv,params);
                    TextView tv = new TextView(activity);
                    Drawable drawable;
                    switch (banner.getTitleColor()){
                        case "red":
                            drawable = ContextCompat.getDrawable(activity,R.drawable.index_banner_tag_red);
                            if (drawable != null)
                                drawable.setAlpha(190);
                            tv.setBackgroundDrawable(drawable);
                            break;
                        case "blue":
                            drawable = ContextCompat.getDrawable(activity,R.drawable.index_banner_tag_blue);
                            if (drawable != null)
                                drawable.setAlpha(190);
                            tv.setBackgroundDrawable(drawable);
                            break;
                        default:
                            drawable = ContextCompat.getDrawable(activity,R.drawable.index_banner_tag_blue);
                            if (drawable != null)
                                drawable.setAlpha(190);
                            tv.setBackgroundDrawable(drawable);
                    }
                    tv.setText(banner.getTypeTitle());
                    tv.setTextSize(12);
                    tv.setTextColor(Color.WHITE);
                    params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.bottomMargin = DisplayUtil.dp2px(activity,30);
                    layout.addView(tv,params);
                    views.add(layout);
                }
                adapter.setBanner(views);
            }
        });
        presenter.getPrivateContent(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    public void initData(){
        dots = new ArrayList<>();
        plBeans = new ArrayList<>();
        plAdapter = new CommPlayListAdapter(activity,plBeans);
        presenter = new PresenterImpl();

//        int[] drawableRes = new int[]{R.drawable.first,R.drawable.second,R.drawable.third,R.drawable.fourth,R.drawable.five,R.drawable.six,R.drawable.seven};
//        for (int drawableRe : drawableRes) {
//            ResizeImageView imageView = new ResizeImageView(activity);
//            imageView.setImageResource(drawableRe);
//            views.add(imageView);
//
//        }

    }

    private void initView() {
        adapter = new LoopImageAdapter(vp);
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(adapter);
        vp.setCurrentItem(1,false);

        daily_today.setText(today());
        iv_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, PrivateFMActivity.class));
            }
        });

        pl_recy.setLayoutManager(new GridLayoutManager(activity,3));
        pl_recy.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position % 3 == 0)
                    outRect.set(0,DisplayUtil.dp2px(activity,16),2,0);
                if (position % 3 == 2)
                    outRect.set(2,DisplayUtil.dp2px(activity,16),0,0);
                else
                    outRect.set(2,DisplayUtil.dp2px(activity,16),2,0);
            }
        });
        pl_recy.setAdapter(plAdapter);
        pl_recy.setFocusable(false);
//        presenter.getPlayList("全部","hot",0,6,new Observer<List<PlayList>>() {
//            private Disposable d;
//            @Override
//            public void onSubscribe(Disposable d) {
//                this.d = d;
//            }
//
//            @Override
//            public void onNext(List<PlayList> playLists) {
//                plBeans.clear();
//                plBeans.addAll(playLists);
//                plAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                d.dispose();
//            }
//
//            @Override
//            public void onComplete() {
//                d.dispose();
//            }
//        });
    }

    private String today(){
        Calendar calendar = GregorianCalendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day);
    }

    class LoopImageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
        private List<View> views;
        private ViewPager vp;

        LoopImageAdapter(ViewPager vp) {
          this.views = new ArrayList<>();
          this.vp = vp;

          ImageView iv = new ImageView(activity);
          iv.setImageResource(R.drawable.placeholder_disk_321);
          iv.setScaleType(ImageView.ScaleType.FIT_XY);
          views.add(iv);
        }
        void setBanner(List<View> list){
            views.clear();
            views.addAll(list);
            if (views.size() > 1){
                dots.clear();
                for (int i = 0; i< views.size(); i++){
                    ImageView dot = new ImageView(activity);
                    dot.setImageResource(R.drawable.vp_dot_selector);
                    dots.add(dot);
                }
                llDots.removeAllViews();
                for (int i=0;i<dots.size();i++){
                    ImageView dot = dots.get(i);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dp2px(activity,6),
                            DisplayUtil.dp2px(activity,6));
                    if (i != dots.size() - 1)
                        params.rightMargin = DisplayUtil.dp2px(activity,4);
                    llDots.addView(dot,params);
                }
                views.add(views.get(0));
                views.add(0, views.get(views.size() - 2));
            }
            notifyDataSetChanged();
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (views.size() > 1)
                vp.setCurrentItem(1,false);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view,params);
            return view;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            if (positionOffset == 0 && mPreviousOffset == 0){
//                if (position == 0)
//                    vp.setCurrentItem(views.size() - 2,false);
//                else if (position == views.size() - 1)
//                    vp.setCurrentItem(1,false);
//                mPreviousOffset = positionOffset;
//
//            }
        }

        @Override
        public void onPageSelected(int position) {
//            params = (RelativeLayout.LayoutParams) llDots.getLayoutParams();
//            params.topMargin = view.getViewHeight() - DisplayUtil.dp2px(activity,16);
//            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
//            llDots.setLayoutParams(params);
            Log.i("dot","onPageSelected");
            if (vp.getCurrentItem() == 0){
                for (int i=0;i<dots.size();i++){
                    if (i == dots.size() - 1){
                        if (!dots.get(i).isSelected())
                            dots.get(i).setSelected(true);
                    }else {
                        if (dots.get(i).isSelected())
                            dots.get(i).setSelected(false);
                    }
                }
            }else if (vp.getCurrentItem() == views.size() - 1){
                for (int i=0;i<dots.size();i++){
                    if (i == 0){
                        if (!dots.get(i).isSelected())
                            dots.get(i).setSelected(true);
                    }else {
                        if (dots.get(i).isSelected())
                            dots.get(i).setSelected(false);
                    }
                }
            }else {
                for (int i = 0; i < dots.size(); i++) {
                    if (i != vp.getCurrentItem() - 1) {
                        if (dots.get(i).isSelected())
                            dots.get(i).setSelected(false);
                    } else
                        dots.get(i).setSelected(true);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE){
                if (vp.getCurrentItem() == 0)
                    vp.setCurrentItem(views.size() - 2,false);
                else if (vp.getCurrentItem() == views.size() - 1)
                    vp.setCurrentItem(1,false);
            }
        }
    }
}
