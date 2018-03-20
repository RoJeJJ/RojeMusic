package com.roje.rojemusic.entities;


import java.io.Serializable;

public class DialogItem implements Serializable{
    public int icn;
    public int text;

    public DialogItem(int icn, int text) {
        this.icn = icn;
        this.text = text;
    }
}
