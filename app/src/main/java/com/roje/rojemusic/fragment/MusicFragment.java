package com.roje.rojemusic.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.google.gson.JsonObject;
import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.MainMusicAdapter;
import com.roje.rojemusic.bean.playlist.Playlist;
import com.roje.rojemusic.db.store.SongSheetColumns;
import com.roje.rojemusic.info.musiclist.BaseItem;
import com.roje.rojemusic.info.musiclist.SheetTabItem;
import com.roje.rojemusic.info.musiclist.MainItem;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.provider.RoJeMusicProvider;
import com.roje.rojemusic.widget.decoration.MMitemDecoration;
import com.roje.rojemusic.utils.CommonUtil;
import com.roje.rojemusic.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MusicFragment extends BaseFragment {
    private Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<BaseItem> itemData;
    private boolean createExpand;
    private boolean collectExpand;
    private List<Playlist> plListCreat;
    private List<Playlist> plliistCollect;
    private MainMusicAdapter adapter;
    MainItem localMusic;
    SheetTabItem createTab;
    SheetTabItem collectTab;
    private int offset = 0;
    private Presenter presenter;
    private long uid;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // 获得授权
            loadLocal();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPlayList();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("create_expand",createExpand);
        outState.putBoolean("collect_expand",collectExpand);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxInit();
        uid = SharedPreferencesUtil.getUid(activity);
        plListCreat = new ArrayList<>();
        plliistCollect = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
    }

    private void rxInit() {
        presenter = new PresenterImpl();
        observer = new MyObserver<List<Playlist>>(activity) {
            @Override
            protected void next(List<Playlist> list) {
                if (list != null){
                    List<Playlist> cr = new ArrayList<>();
                    List<Playlist> co = new ArrayList<>();
                    for (Playlist pl:list){
                        if (pl.getUserId() == uid)
                            cr.add(pl);
                        else
                            co.add(pl);
                    }
                    adapter.refresh(createTab,cr);
                    adapter.refresh(collectTab,co);
                    if (refreshLayout.isRefreshing())
                        refreshLayout.setRefreshing(false);
                }
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            createExpand = savedInstanceState.getBoolean("create_expand", true);
            collectExpand = savedInstanceState.getBoolean("collect_expand",false);
        }else {
            createExpand = true;
            collectExpand = false;
        }
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        initViews();
//       load();
        return rootView;
    }

    private MyObserver<List<Playlist>> observer;

    private void initData() {
        itemData = new ArrayList<>();
        localMusic = new MainItem(getString(R.string.music_local),0,
                R.drawable.music_icn_local);
        itemData.add(localMusic);
        itemData.add(new MainItem(getString(R.string.music_recent),0,
                R.drawable.music_icn_recent));
        itemData.add(new MainItem(getString(R.string.music_download),0,
                R.drawable.music_icn_dld));
        itemData.add(new MainItem(getString(R.string.music_fm),0,
                R.drawable.music_icn_dj));
        itemData.add(new MainItem(getString(R.string.music_fav),0,
                R.drawable.music_icn_mv));
        createTab = new SheetTabItem(SheetTabItem.Type.title_type_create,plListCreat);
        createTab.setExpand(createExpand);
        collectTab = new SheetTabItem(SheetTabItem.Type.title_type_collect,plliistCollect);
        collectTab.setExpand(collectExpand);
        itemData.add(createTab);
        itemData.add(collectTab);
    }

    private void initViews() {
        refreshLayout.setColorSchemeColors(ThemeUtils.getColorById(activity,R.color.theme_primary_color));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                load();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        recycler.addItemDecoration(new MMitemDecoration(activity));
        adapter = new MainMusicAdapter(activity,itemData);
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void loadLocal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            getLoaderManager().initLoader(0,null,loaderCallbacks);
        }
//        getLoaderManager().initLoader(5,null,loaderCallbacks);
    }

    private void loadPlayList(){
        if (uid != -1){
            JsonObject object = new JsonObject();
            object.addProperty("uid",uid);
            object.addProperty("offset",offset);
            object.addProperty("limit",30);
            presenter.getUserPlaylist(object,observer);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            switch (i){
                case 0:
                    String selection = "1=1 AND title != '' AND "+MediaStore.Audio.Media.SIZE+">"+CommonUtil.FILE_SIZE+
                            " AND "+ MediaStore.Audio.Media.DURATION+">"+CommonUtil.DURATION;
                    return new CursorLoader(activity, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,new String[]{"COUNT(*)"},selection,null,null);
                case 5:
                    return new CursorLoader(activity,RoJeMusicProvider.SONG_SHEET_URI,null,null,null,null);
            }
            return null;
        }
        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()){
                case 0:
                    cursor.moveToFirst();
                    localMusic.setCount(cursor.getInt(0));
                    adapter.notifyItemChanged(localMusic.getPosition());
                    break;
                case 5:
                    plListCreat.clear();
                    if (cursor.moveToFirst()){
                        do {
                            Playlist sheet = new Playlist();
                            long id = cursor.getLong(cursor.getColumnIndexOrThrow(SongSheetColumns.id));
                            String name = cursor.getString(cursor.getColumnIndexOrThrow(SongSheetColumns.name));
                            int count = cursor.getInt(cursor.getColumnIndexOrThrow(SongSheetColumns.trackCount));
                            String coverImg = cursor.getString(cursor.getColumnIndexOrThrow(SongSheetColumns.coverImgUrl));
                            sheet.setId(id);
                            sheet.setName(name);
                            sheet.setTrackCount(count);
                            sheet.setCoverImgUrl(coverImg);
                            plListCreat.add(sheet);
                        }while (cursor.moveToNext());
//                        if (plListCreat.size() > 0){
//                            createTab.set
//                            adapter.notifyItemRangeChanged(crea,temp.size());
//                        }
                    }
                    break;
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    };
}
