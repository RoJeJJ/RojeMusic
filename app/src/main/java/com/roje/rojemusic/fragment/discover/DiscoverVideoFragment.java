package com.roje.rojemusic.fragment.discover;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.TopMvAdapter;
import com.roje.rojemusic.bean.topmv.MvBean;
import com.roje.rojemusic.fragment.BaseFragment;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;


public class DiscoverVideoFragment extends BaseFragment {
    @BindView(R.id.mv_cecy)
    RecyclerView mvRecy;
    private Presenter presenter;
    private Observer<List<MvBean>> mvlistObserver;
    private TopMvAdapter adapter;
    private boolean loadMore = false;
    private int offset = 0;
    public DiscoverVideoFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxInit();
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        mvlistObserver = new MyObserver<List<MvBean>>(activity) {
            @Override
            protected void next(List<MvBean> mvBeans) {
                if (!loadMore)
                    adapter.setData(mvBeans);
                else {
                    offset = 0;
                    adapter.addData(mvBeans);
                }
            }
        };
    }

    public static DiscoverVideoFragment newInstance(){
        return new DiscoverVideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.discover_video_fragment,container,false);
        ButterKnife.bind(this,root);
        initViews();
        return root;
    }

    private void initViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
        mvRecy.setLayoutManager(layoutManager);
        mvRecy.setAdapter(adapter = new TopMvAdapter(activity,new ArrayList<MvBean>()));
        mvRecy.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = DisplayUtil.dp2px(activity,2);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        reqData(false);
    }
    private void reqData(boolean loadMore){
        this.loadMore = loadMore;
        JsonObject reqJson = new JsonObject();
        if (loadMore){
            reqJson.addProperty("offset",++offset);
            reqJson.addProperty("total",true);
            reqJson.addProperty("limit",30);
        }else {
            reqJson.addProperty("offset",0);
            reqJson.addProperty("total",true);
            reqJson.addProperty("limit",30);
        }
        presenter.topMV(reqJson,mvlistObserver);
    }
}
