package com.roje.rojemusic.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.R;
import com.roje.rojemusic.RjApplication;
import com.roje.rojemusic.bean.event.EventRespBean;
import com.roje.rojemusic.bean.event.VideoResp;
import com.roje.rojemusic.ijk.media.example.widget.media.IjkVideoView;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.EncryptTool;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.LogUtil;
import com.roje.rojemusic.widget.text.JumpMovementMethod;
import com.roje.rojemusic.widget.transformer.CircleBitmapTransform;
import com.roje.rojemusic.widget.view.VideoPlayerIJK;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * todo 分页加载数据有问题
 *
 */
public class FriendsFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private FriendsEventAdapter adapter;
    private Presenter presenter;
    private Observer<List<EventRespBean.EventBean>> observer;
    private List<Long> oldIds;
    private List<Long> newIds;
    private List<EventRespBean.EventBean> beanList;
    private Drawable divider;
    private Disposable d;
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            request();
        }
    };
    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        rxInit();
    }

    private void initData() {
        oldIds = new ArrayList<>();
        newIds = new ArrayList<>();
        beanList = new ArrayList<>();
        divider = ContextCompat.getDrawable(activity,R.drawable.divider);
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new Observer<List<EventRespBean.EventBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                FriendsFragment.this.d = d;
            }

            @Override
            public void onNext(List<EventRespBean.EventBean> eventBeans) {
                beanList.clear();
                beanList.addAll(eventBeans);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                if (refreshLayout.isRefreshing())
                    refreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                if (refreshLayout.isRefreshing())
                    refreshLayout.setRefreshing(false);
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this,view);
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void request(){
        if (RjApplication.login){
            presenter.event(observer);
        }else {
            if (refreshLayout.isRefreshing())
                refreshLayout.setRefreshing(false);
            Toast.makeText(activity,"请先登录",Toast.LENGTH_SHORT).show();
        }
    }
    private void initViews() {
        recycler.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter = new FriendsEventAdapter());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(activity,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(divider);
        recycler.addItemDecoration(itemDecoration);
        refreshLayout.setColorSchemeColors(ThemeUtils.getColorById(activity,R.color.theme_primary_color));
        refreshLayout.setOnRefreshListener(refreshListener);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            refreshLayout.setRefreshing(true);
            refreshListener.onRefresh();
        }
    }

    class FriendsEventAdapter extends RecyclerView.Adapter<FriendsEventAdapter.Holder>{

        private Gson gson;
        private SimpleDateFormat dateFormat;
        FriendsEventAdapter(){
            dateFormat = new SimpleDateFormat("M月dd", Locale.CHINA);
            gson = new Gson();
        }
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.friend_item_layout,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            int type = 0;
            EventRespBean.EventBean bean = beanList.get(position);
            String json = bean.getJson();
            JsonObject object = new JsonParser().parse(json).getAsJsonObject();
            if (object.has("song"))
                type = 1;
            else if (object.has("video"))
                type = 2;
            Glide.with(activity).load(bean.getUser().getAvatarUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleBitmapTransform()))
                    .into(holder.avatar);
            if (bean.getUser().getExperts() != null){
                holder.daren.setVisibility(View.VISIBLE);
                Glide.with(activity).load(R.drawable.icn_daren_48).into(holder.daren);
            }else
                holder.daren.setVisibility(View.GONE);
            String t;
            switch (type){
                case 1:
                    t = getString(R.string.share_song,bean.getUser().getNickname());
                    holder.video.setVisibility(View.GONE);
                    holder.songFrame.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    t = getString(R.string.share_video,bean.getUser().getNickname());
                    holder.video.setVisibility(View.VISIBLE);
                    holder.songFrame.setVisibility(View.GONE);
                    VideoResp.Video video = gson.fromJson(object.get("video"), VideoResp.Video.class);
                   holder.video.setVideo(video);
                    break;
                default:
                        t = bean.getUser().getNickname();
            }
            holder.title.setMovementMethod(JumpMovementMethod.getInstance(ContextCompat.getColor(activity,R.color.linkTextbg)));
            SpannableStringBuilder spanBuilder = new SpannableStringBuilder(t);
            spanBuilder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(ContextCompat.getColor(activity,R.color.linkText));
                    ds.setUnderlineText(false);
                }
            },0,bean.getUser().getNickname().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.title.setText(spanBuilder);
            holder.date.setText(dateFormat.format(new Date(bean.getEventTime())));
            holder.textBody.setText(object.get("msg").getAsString());
            holder.tag.setText(bean.getRcmdInfo().getReason());
            holder.praise.setText(String.valueOf(bean.getInfo().getLikedCount()));
            holder.praise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("tag","onclick");
                }
            });
            holder.cmt.setText(String.valueOf(bean.getInfo().getCommentCount()));
            holder.share.setText(String.valueOf(bean.getInfo().getShareCount()));
        }

        @Override
        public int getItemCount() {
            return beanList.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            @BindView(R.id.avatarImg)
            ImageView avatar;
            @BindView(R.id.daren)
            ImageView daren;
            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.date)
            TextView date;
            @BindView(R.id.textBody)
            TextView textBody;
            @BindView(R.id.video)
            VideoPlayerIJK video;
            @BindView(R.id.count)
            TextView count;
            @BindView(R.id.tag)
            TextView tag;
            @BindView(R.id.praise)
            TextView praise;
            @BindView(R.id.cmt)
            TextView cmt;
            @BindView(R.id.share)
            TextView share;
            @BindView(R.id.more)
            ImageView more;
            @BindView(R.id.songFrame)
            LinearLayout songFrame;
            Holder(View itemView){
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
