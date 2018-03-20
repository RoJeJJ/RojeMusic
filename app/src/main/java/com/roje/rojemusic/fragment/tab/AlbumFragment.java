package com.roje.rojemusic.fragment.tab;

import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;
import com.roje.rojemusic.R;
import com.roje.rojemusic.entities.Album;
import com.roje.rojemusic.fragment.BaseFragment;
import com.roje.rojemusic.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlbumFragment extends BaseFragment {
    private String title;
    private List<Album> albums;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.load_anim)
    ImageView load_anim;
    @BindView(R.id.load)
    View loadView;
    private SingleSongLoaderCallbacks loaderCallbacks;
    private AlbumListAdapter adapter;
    public static AlbumFragment newInstance(){
        return new AlbumFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albums = new ArrayList<>();
        loaderCallbacks = new SingleSongLoaderCallbacks();
    }

    public String getTitle(){
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        ButterKnife.bind(this,view);
        rv.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        adapter = new AlbumListAdapter();
        rv.setAdapter(adapter);
        AnimationDrawable animationDrawable = (AnimationDrawable) load_anim.getDrawable();
        animationDrawable.start();
        getLoaderManager().initLoader(0,null,loaderCallbacks);
        return view;
    }

    class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.main_music_playlist_item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Album album = albums.get(position);
            holder.cover.setImageResource(R.drawable.list_cover_alb);
            holder.title.setText(album.getAlbum_name());
            holder.sub.setText(activity.getString(R.string.album_subtext,album.getNumber_of_songs(),album.getAlbum_artist()));
        }

        @Override
        public int getItemCount() {
            return albums.size();
        }

        class Holder extends RecyclerView.ViewHolder{

            @BindView(R.id.pl_title)
            TextView title;
            @BindView(R.id.pl_count)
            TextView sub;
            @BindView(R.id.pl_cover)
            ImageView cover;
            @BindView(R.id.set)
            ImageView more;
            Holder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
    class SingleSongLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String selection = MediaStore.Audio.Albums._ID+
                    " in(select distinct "+ MediaStore.Audio.Media.ALBUM_ID+
                    " from audio_meta where (1=1) " +
                    " and "+ MediaStore.Audio.Media.SIZE+" > "+
                    CommonUtil.FILE_SIZE+" and "+ MediaStore.Audio.Media.DURATION+
                    " > "+CommonUtil.DURATION+")";
            return new CursorLoader(activity, MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,null,selection,null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()){
                case 0:
                    if (cursor.moveToFirst()){
                        albums.clear();
                        do {
                            Album album = new Album();
                            album.setAlbum_art(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));
                            album.setAlbum_artist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST)));
                            album.setAlbum_id(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums._ID)));
                            album.setAlbum_name(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
                            album.setNumber_of_songs(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS)));
                            album.setAlbum_sort(Pinyin.toPinyin(album.getAlbum_name().charAt(0)).substring(0, 1).toUpperCase());
                            albums.add(album);
                        }while (cursor.moveToNext());
                        if (loadView.getVisibility() == View.VISIBLE){
                            loadView.setVisibility(View.GONE);
                            rv.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }
                    }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    }
}
