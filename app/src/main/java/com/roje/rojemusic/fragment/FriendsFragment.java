package com.roje.rojemusic.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roje.rojemusic.R;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;


public class FriendsFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private FriendsEventAdapter adapter;
    private Presenter presenter;
    private Observer<String> observer;
    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxInit();
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new MyObserver<String>(activity) {
            @Override
            protected void next(String s) {

            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
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
//        recycler.setAdapter(adapter = new );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //更新数据
        }
    }

    class FriendsEventAdapter extends RecyclerView.Adapter<FriendsEventAdapter.Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class Holder extends RecyclerView.ViewHolder{
            Holder(View itemView){
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
