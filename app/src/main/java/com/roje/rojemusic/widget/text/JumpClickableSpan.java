package com.roje.rojemusic.widget.text;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;


public abstract class JumpClickableSpan extends ClickableSpan {
    private int bgColor;
    private int defaultBgColor;
    public JumpClickableSpan(){
        defaultBgColor = Color.TRANSPARENT;
        this.bgColor = defaultBgColor;
    }
    public int getDefaultBgColor(){
        return defaultBgColor;
    }
    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.bgColor = bgColor;
    }
    public void setBgColor(int color){
        this.bgColor = color;
    }
}
