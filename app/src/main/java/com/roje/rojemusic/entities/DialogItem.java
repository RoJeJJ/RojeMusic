package com.roje.rojemusic.entities;


import java.io.Serializable;

public class DialogItem implements Serializable{
    public int icn;
    public String title;

    public DialogItem(int icn, String text) {
        this.icn = icn;
        this.title = text;
    }
}
