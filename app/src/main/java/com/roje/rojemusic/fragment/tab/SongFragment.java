package com.roje.rojemusic.fragment.tab;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
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
import com.roje.rojemusic.db.SortOrder;
import com.roje.rojemusic.entities.Song;
import com.roje.rojemusic.fragment.BaseFragment;
import com.roje.rojemusic.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SongFragment extends BaseFragment {
    private String title;
    private List<Song> songs;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.load_anim)
    ImageView load_anim;
    @BindView(R.id.load)
    View loadView;
    private SingleSongLoaderCallbacks loaderCallbacks;
    private SongListAdapter adapter;
    public static SongFragment newInstance(){
        return new SongFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songs = new ArrayList<>();
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
        rv.addItemDecoration(new SongListDecoration(activity));
        adapter = new SongListAdapter();
        rv.setAdapter(adapter);
        AnimationDrawable animationDrawable = (AnimationDrawable) load_anim.getDrawable();
        animationDrawable.start();
        getLoaderManager().initLoader(0,null,loaderCallbacks);
        return view;
    }

    class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.song_list_item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Song song = songs.get(position);
            holder.title.setText(song.getMusicName());
            holder.sub.setText(activity.getString(R.string.artistAndAlbum,song.getArtist(),song.getAlbumName()));
        }

        @Override
        public int getItemCount() {
            return songs.size();
        }

        class Holder extends RecyclerView.ViewHolder{

            @BindView(R.id.title)
            TextView title;
            @BindView(R.id.subText)
            TextView sub;
            @BindView(R.id.dl_ok)
            ImageView dl;
            @BindView(R.id.quality)
            ImageView qua;
            @BindView(R.id.more)
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
            String selection = "1=1 AND title != '' AND "+MediaStore.Audio.Media.SIZE+">"+CommonUtil.FILE_SIZE+
                    " AND "+ MediaStore.Audio.Media.DURATION+">"+CommonUtil.DURATION;
            return new CursorLoader(activity, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,selection,null, SortOrder.SongSortOrder.SORT_A_Z);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()){
                case 0:
                    if (cursor.moveToFirst()){
                        songs.clear();
                        do {
                            Song song = new Song();
                            song.setSongId(cursor.getInt(cursor
                                    .getColumnIndex(MediaStore.Audio.Media._ID)));
                            song.setAlbumId(cursor.getInt(cursor
                                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                            song.setAlbumName(cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Albums.ALBUM)));
                            song.setAlbumData(CommonUtil.getAlbumArtUri(song.getAlbumId())+"");
                            song.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                            song.setMusicName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                            song.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                            song.setArtistId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID)));
                            String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                            song.setData(filePath);
                            song.setFolder(filePath.substring(0,filePath.lastIndexOf(File.separator)));
                            song.setSize(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
                            song.setLocal(true);
                            song.setSort(Pinyin.toPinyin(song.getMusicName().charAt(0)).substring(0,1).toUpperCase());
                            songs.add(song);
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

    class SongListDecoration extends RecyclerView.ItemDecoration{
        private Drawable drawer;
        SongListDecoration(Context context){
            drawer = ContextCompat.getDrawable(context,R.drawable.divider);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            for (int i=0;i<parent.getChildCount();i++){
                View child = parent.getChildAt(i);
                SongListAdapter.Holder holder= (SongListAdapter.Holder) parent.getChildViewHolder(child);
                ViewGroup viewParent = (ViewGroup) holder.title.getParent();
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = viewParent.getLeft() + holder.title.getLeft();
                int top = child.getBottom() + params.bottomMargin + Math.round(child.getTranslationY());
                int right = parent.getWidth() - parent.getPaddingRight();
                int bottom = top + drawer.getIntrinsicHeight();
                drawer.setBounds(left,top,right,bottom);
                drawer.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,0,drawer.getIntrinsicHeight());
        }
    }
}
