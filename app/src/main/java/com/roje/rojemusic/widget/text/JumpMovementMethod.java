package com.roje.rojemusic.widget.text;

import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;


public class JumpMovementMethod extends LinkMovementMethod {
    private int clickColor;
    private static JumpMovementMethod sInstance;
    private BackgroundColorSpan bgSpan;
    private float lastX,lastY;
    private int touchSlop;
    public static JumpMovementMethod getInstance(int clickColor){
        if (sInstance == null)
            sInstance = new JumpMovementMethod(clickColor);
        return sInstance;
    }
    private JumpMovementMethod(int clickColor){
        this.clickColor = clickColor;
        touchSlop = new ViewConfiguration().getScaledTouchSlop();
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
                    buffer.removeSpan(bgSpan);
                    if (event.getY() < line * lineHeight || event.getY() > (line+1)*lineHeight) {
                        return true;
                    }else {
                        links[0].onClick(widget);
                    }
                } else if (action == MotionEvent.ACTION_DOWN) {
                    lastX = event.getX();
                    lastY = event.getY();
                    buffer.setSpan(bgSpan = new BackgroundColorSpan(clickColor),
                            buffer.getSpanStart(links[0]),
                            buffer.getSpanEnd(links[0]),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }else {
                    if (Math.abs(event.getX() - lastX) > touchSlop || Math.abs(event.getY() - lastY) > touchSlop)
                        buffer.removeSpan(bgSpan);
                }
                return true;
            } else {
                    buffer.removeSpan(bgSpan);
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }
}
