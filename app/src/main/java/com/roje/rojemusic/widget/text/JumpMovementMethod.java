package com.roje.rojemusic.widget.text;

import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;


public class JumpMovementMethod extends LinkMovementMethod {
    private int clickColor;
    private static JumpMovementMethod sInstance;
    private BackgroundColorSpan bgSpan;
    private float oldX;
    public static JumpMovementMethod getInstance(int clickColor){
        if (sInstance == null)
            sInstance = new JumpMovementMethod(clickColor);
        return sInstance;
    }
    private JumpMovementMethod(int clickColor){
        this.clickColor = clickColor;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            int lineHeight = widget.getLineHeight();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);
            ClickableSpan[] links = buffer.getSpans(off, off, ClickableSpan.class);
            if (links.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    Log.i("event","up");
                    buffer.removeSpan(bgSpan);
                    if (event.getY() < line * lineHeight || event.getY() > (line+1)*lineHeight) {
                        return true;
                    }else {
                        links[0].onClick(widget);
                    }
//                    Selection.removeSelection(buffer);
                } else if (action == MotionEvent.ACTION_DOWN) {
                    Log.i("event","down");
                    oldX = event.getX();
                    buffer.setSpan(bgSpan = new BackgroundColorSpan(clickColor),
                            buffer.getSpanStart(links[0]),
                            buffer.getSpanEnd(links[0]),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }else {
                    Log.i("event","move");
                    if (event.getY() < line * lineHeight || event.getY() > (line+1)*lineHeight) {
                        buffer.removeSpan(bgSpan);
                        return true;
                    }
                    if (off == buffer.getSpanEnd(links[0]) && event.getX() > oldX){
                        buffer.removeSpan(bgSpan);
                        oldX = event.getX();
                        return true;
                    }
                    oldX = event.getX();
                }
                return true;
            } else {
                buffer.removeSpan(bgSpan);
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }
}
