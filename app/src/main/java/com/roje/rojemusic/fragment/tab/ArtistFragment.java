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
import com.roje.rojemusic.entities.Artist;
import com.roje.rojemusic.fragment.BaseFragment;
import com.roje.rojemusic.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArtistFragment extends BaseFragment {
    private String title;
    private List<Artist> artists;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.load_anim)
    ImageView load_anim;
    @BindView(R.id.load)
    View loadView;
    private SingleSongLoaderCallbacks loaderCallbacks;
    private ArtistListAdapter adapter;
    public static ArtistFragment newInstance(){
        return new ArtistFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artists = new ArrayList<>();
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
        adapter = new ArtistListAdapter();
        rv.setAdapter(adapter);
        AnimationDrawable animationDrawable = (AnimationDrawable) load_anim.getDrawable();
        animationDrawable.start();
        getLoaderManager().initLoader(0,null,loaderCallbacks);
        return view;
    }

    class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.main_music_playlist_item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Artist artist = artists.get(position);
            holder.title.setText(artist.getArtist_name());
            holder.count.setText(activity.getString(R.string.pl_count,artist.getNumber_of_tracks()));
        }

        @Override
        public int getItemCount() {
            return artists.size();
        }

        class Holder extends RecyclerView.ViewHolder{

            @BindView(R.id.pl_title)
            TextView title;
            @BindView(R.id.pl_count)
            TextView count;
            @BindView(R.id.pl_cover)
            ImageView cover;
            @BindView(R.id.set)
            ImageView play;
            Holder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
    class SingleSongLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String selection = MediaStore.Audio.Artists._ID+
                    " in(select distinct "+ MediaStore.Audio.Media.ARTIST_ID+
                    " from audio_meta where (1=1) and "+ MediaStore.Audio.Media.SIZE+
                    " > "+ CommonUtil.FILE_SIZE+" and "+
                    MediaStore.Audio.Media.DURATION+" > "+CommonUtil.DURATION+")";
            return new CursorLoader(activity, MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,null,selection,null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()){
                case 0:
                    if (cursor.moveToFirst()){
                        artists.clear();
                        do {
                            Artist artist = new Artist();
                            artist.setArtist_name(cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
                            artist.setNumber_of_tracks(cursor.getInt(cursor
                                    .getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)));
                            artist.setArtist_id(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Artists._ID)));
                            artist.setArtist_sort(Pinyin.toPinyin(artist.getArtist_name().charAt(0)).substring(0, 1).toUpperCase());
                            artists.add(artist);
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
