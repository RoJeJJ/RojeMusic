package com.roje.rojemusic.widget.ricktext;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.BaseMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import com.roje.rojemusic.R;


public class ClickMovementMethod extends BaseMovementMethod {
    private static ClickMovementMethod sInstance;
    private Context mContext;
    public static ClickMovementMethod getInstance(Context context){
        if (sInstance == null)
            sInstance = new ClickMovementMethod(context);
        return sInstance;
    }
    private ClickMovementMethod(Context context){
        this.mContext = context;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] links = buffer.getSpans(off, off, ClickableSpan.class);
            int start = 0,end = 0;
            if (links.length != 0) {
                start = buffer.getSpanStart(links[0]);
                end = buffer.getSpanEnd(links[0]);
                if (action == MotionEvent.ACTION_UP) {
                    buffer.setSpan(new BackgroundColorSpan(Color.TRANSPARENT),
                            buffer.getSpanStart(links[0]),buffer.getSpanEnd(links[0]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    links[0].onClick(widget);
                } else {
                    buffer.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.linkTextbg)),
                            buffer.getSpanStart(links[0]),buffer.getSpanEnd(links[0]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Selection.setSelection(buffer,
                            buffer.getSpanStart(links[0]),
                            buffer.getSpanEnd(links[0]));
                }
            } else {
                Selection.removeSelection(buffer);
//                Selection.removeSelection(buffer);
            }
        }

        return super.onTouchEvent(widget, buffer, event);
    }
    @Override
    public void initialize(TextView widget, Spannable text) {
        Selection.removeSelection(text);
    }
}
