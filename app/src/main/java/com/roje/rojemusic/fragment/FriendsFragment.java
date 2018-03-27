package com.roje.rojemusic.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.event.EventRespBean;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.widget.text.JumpClickableSpan;
import com.roje.rojemusic.widget.text.JumpMovementMethod;
import com.roje.rojemusic.widget.transformer.CircleBitmapTransform;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;

/**
 * todo 分页加载数据有问题
 *
 */
public class FriendsFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private FriendsEventAdapter adapter;
    private Presenter presenter;
    private Observer<List<EventRespBean.EventBean>> observer;
    private List<Long> oldIds;
    private List<Long> newIds;
    private List<EventRespBean.EventBean> beanList;
    private Drawable divider;
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
        observer = new MyObserver<List<EventRespBean.EventBean>>(activity) {
            @Override
            protected void next(List<EventRespBean.EventBean> s) {
                beanList.clear();
                beanList.addAll(s);
                adapter.notifyDataSetChanged();
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
        presenter.event(observer);
    }

    private void initViews() {
        recycler.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter = new FriendsEventAdapter());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(activity,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(divider);
        recycler.addItemDecoration(itemDecoration);
    }

    class FriendsEventAdapter extends RecyclerView.Adapter<FriendsEventAdapter.Holder>{

        private SimpleDateFormat dateFormat;
        FriendsEventAdapter(){
            dateFormat = new SimpleDateFormat("M月dd", Locale.CHINA);
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
            String t = "";
            switch (type){
                case 1:
                    t = getString(R.string.share_song,bean.getUser().getNickname());
                    break;
                case 2:
                    t = getString(R.string.share_video,bean.getUser().getNickname());
                    break;
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
            if (type == 2){
                Glide.with(activity).load(object.get("video").getAsJsonObject().get("coverUrl").getAsString()).into(holder.cover);
            }
            holder.tag.setText(bean.getRcmdInfo().getReason());
            holder.praise.setText(String.valueOf(bean.getInfo().getLikedCount()));
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
            @BindView(R.id.cover)
            ImageView cover;
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
            Holder(View itemView){
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
