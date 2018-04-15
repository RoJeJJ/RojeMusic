package com.roje.rojemusic.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.roje.rojemusic.R;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RankingListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    TintToolbar toolbar;
    private Presenter presenter;
    private Observer<String> plCountOb = new Observer<String>() {
        private Disposable d;
        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
        }

        @Override
        public void onNext(String s) {
            d.dispose();
        }

        @Override
        public void onError(Throwable e) {
            d.dispose();
        }

        @Override
        public void onComplete() {
            d.dispose();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);
        ButterKnife.bind(this);
        setToolbar();
        presenter = new PresenterImpl();
        presenter.plCount(plCountOb);
    }

    private void setToolbar() {
       super.initToolbar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
