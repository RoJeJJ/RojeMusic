package com.roje.rojemusic.ui.decoration;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.roje.rojemusic.R;
import com.roje.rojemusic.adapter.MusicMainAdapter;
import com.roje.rojemusic.adapter.holder.MainItemHolder;
import com.roje.rojemusic.adapter.holder.PlayListHolder;

public class MMitemDecoration extends RecyclerView.ItemDecoration{
    private Drawable drawer;
    public MMitemDecoration(Context context){
        drawer = ContextCompat.getDrawable(context,R.drawable.divider);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i=0;i<parent.getChildCount();i++){
            View child = parent.getChildAt(i);
            if (parent.getChildViewHolder(child) instanceof MainItemHolder) {
                MainItemHolder holder = (MainItemHolder) parent.getChildViewHolder(child);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = holder.title_ll.getLeft();
                int top = child.getBottom() + params.bottomMargin;
                int right = parent.getWidth() - parent.getPaddingRight();
                int bottom = child.getBottom() + drawer.getIntrinsicHeight();
                drawer.setBounds(left, top, right, bottom);
                drawer.draw(c);
            }else if (parent.getChildViewHolder(child) instanceof PlayListHolder){
                PlayListHolder holder = (PlayListHolder) parent.getChildViewHolder(child);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = holder.title_ll.getLeft();
                int top = child.getBottom() + params.bottomMargin;
                int right = parent.getWidth() - parent.getPaddingRight();
                int bottom = child.getBottom() + drawer.getIntrinsicHeight();
                drawer.setBounds(left, top, right, bottom);
                drawer.draw(c);
            }
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildViewHolder(view) instanceof MusicMainAdapter.MainItemHolder)
            outRect.set(0,0,0,drawer.getIntrinsicHeight());
        else if (parent.getChildViewHolder(view) instanceof MusicMainAdapter.SheetListItemHolder)
            outRect.set(0,0,0,drawer.getIntrinsicHeight());
    }
}
