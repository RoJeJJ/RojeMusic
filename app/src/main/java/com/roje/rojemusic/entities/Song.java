package com.roje.rojemusic.entities;


import android.os.Parcel;
import android.os.Parcelable;


public class Song implements Parcelable{
    private long songId = -1;
    private int albumId = -1;
    private String albumName;
    private String albumData;
    private int duration;
    private String musicName;
    private String artist;
    private long artistId;
    private String data;
    private String folder;
    private String lrc;
    private boolean isLocal;
    private String sort;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Song(){
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumData() {
        return albumData;
    }

    public void setAlbumData(String albumData) {
        this.albumData = albumData;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public static Creator<Song> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(songId);
        parcel.writeInt(albumId);
        parcel.writeString(albumName);
        parcel.writeString(albumData);
        parcel.writeInt(duration);
        parcel.writeString(musicName);
        parcel.writeString(artist);
        parcel.writeLong(artistId);
        parcel.writeString(data);
        parcel.writeString(folder);
        parcel.writeString(lrc);
        parcel.writeByte((byte)(isLocal ?1:0));
        parcel.writeString(sort);
        parcel.writeInt(size);
    }
    private static final Parcelable.Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }

        @Override
        public Song[] newArray(int i) {
            return new Song[0];
        }
    };

    private Song(Parcel parcel){
        songId = parcel.readLong();
        albumId = parcel.readInt();
        albumName = parcel.readString();
        albumData = parcel.readString();
        duration = parcel.readInt();
        musicName = parcel.readString();
        artist = parcel.readString();
        artistId = parcel.readLong();
        data = parcel.readString();
        folder = parcel.readString();
        lrc = parcel.readString();
        isLocal = parcel.readByte() == 1;
        sort = parcel.readString();
        size = parcel.readInt();
    }
}
