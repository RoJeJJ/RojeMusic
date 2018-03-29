package com.roje.rojemusic.fragment.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roje.rojemusic.R;
import com.roje.rojemusic.entities.Song;
import com.roje.rojemusic.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FolderFragment extends BaseFragment {
    private String title;
    private List<Song> songs;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.load)
    View loadView;
    public static FolderFragment newInstance(){
        return new FolderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = "";
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        songs = new ArrayList<>();
    }

    public String getTitle(){
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        ButterKnife.bind(this,view);
//        rv.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
//        adapter = new SongListAdapter();
//        rv.setAdapter(adapter);
//        AnimationDrawable animationDrawable = (AnimationDrawable) load_anim.getDrawable();
//        animationDrawable.start();
//        getLoaderManager().initLoader(0,null,loaderCallbacks);
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
            holder.sub.setText(activity.getString(R.string.na_na,song.getArtist(),song.getAlbumName()));
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
}
