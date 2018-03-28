package com.roje.rojemusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailySongsActivity extends AppCompatActivity {

    @BindView(R.id.dailyRecycle)
    RecyclerView dailyRecycle;
    private Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_songs);
        ButterKnife.bind(this);
        initData();
        initViews();
        request();
    }

    private void initViews() {
        dailyRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void initData() {
        presenter = new PresenterImpl();
    }

    private void request(){
        JsonObject o = new JsonObject();
        o.addProperty("offset",0);
        o.addProperty("total",true);
        o.addProperty("limit",20);
        presenter.recommendSong(o);
    }
    class DailySongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return 0;
            else if (position == 1)
                return 1;
            else
                return 2;
        }
    }
}
