package com.roje.rojemusic.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;


public class DialogScrollLayout extends RelativeLayout implements NestedScrollingParent2 {
    private int axes = 0;
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        this.axes = axes;
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        if (axes != 0) {
            if (getTop() != 0) {
                if (getTop() < getHeight() / 2)
                    startScroll(getTop(),0);
                else {
                    startScroll(getTop(),2*getTop());
                }
            }
        }
        axes = 0;
    }


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (axes != 0) {
            if (dyConsumed == 0 && dyUnconsumed < 0) {
                layout(getLeft(),getTop() - dyUnconsumed,getRight(),getTop() - dyUnconsumed+getHeight());
            }
        }
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @Nullable int[] consumed, int type) {
        Log.i("dy", dy + "");
        if (axes != 0) {
            if (getTop() > 0) {
                if (dy > 0 && dy > getTop()) { //上滑
                    layout(getLeft(), 0, getRight(), getHeight());
                    if (consumed != null)
                        consumed[1] = getTop();
                } else {
                    layout(getLeft(), getTop() - dy, getRight(), getTop() - dy + getHeight());
                    if (consumed != null)
                        consumed[1] = dy;
                }
            }
        }
    }


    public interface DisMissListener {
        void disMiss();
    }


    /**
     * 屏幕高度
     */
    private int mScreenHeight;
    /**
     * 相对父布局的top
     */


    public DialogScrollLayout(Context context) {
        this(context, null);
    }

    public DialogScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private DisMissListener listener;

    public void setDisMissListener(DisMissListener listener) {
        this.listener = listener;
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                downY = ev.getRawY();
//                mLastMoveY = downY;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                /*
//      手机当时的屏幕坐标
//     */
//                float moveY = ev.getRawY();
//                float diff = moveY - downY;
//                if (diff > 0 && Math.abs(diff) >= mTouchSlop){ //向下滑动
//                    if (!view.canScrollVertically(-1)){ //recyclerview不能向下滚动, 滚动到顶了
//                        return true;
//                    }
//                }
//                if (diff < 0 &&  Math.abs(diff) > mTouchSlop){//向上滑动
//                    if (!view.canScrollVertically(1)){//recyclerview不能向上滚动, 滚动到顶了
//                        return true;
//                    }
//                }
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_MOVE:
//                int scrollY = (int) (mLastMoveY - event.getRawY());
//                int top = getTop() - scrollY;
//                if (top < 0)
//                    top = 0;
//                int bottom = top+getHeight();
//                layout(getLeft(),top,getRight(),bottom);
//                mLastMoveY = event.getRawY();
//                if (top >= getHeight() && listener != null)
//                    listener.disMiss();
//
//                break;
//            case MotionEvent.ACTION_UP:
//                if (getTop() > getHeight() / 3)
//                    startScroll(getTop(),getHeight());
//                else
//                    startScroll(getTop(),0);
//                performClick();
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

    private void startScroll(final int startY, final int endY) {
        ValueAnimator animator = ValueAnimator.ofFloat(startY, endY);
        animator.setDuration(100);
        if (startY >= endY)
            animator.setInterpolator(new DecelerateInterpolator());
        else
            animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int top = (int) ((float) animation.getAnimatedValue());
                layout(getLeft(), top, getRight(), top + getHeight());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setEnabled(true);

                if (endY != 0) {
                    if (listener != null)
                        listener.disMiss();
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                setEnabled(false);
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getMeasuredHeight() > mScreenHeight / 2) {
            int heightMS = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) / 2, MeasureSpec.getMode(heightMeasureSpec));
            super.onMeasure(widthMeasureSpec, heightMS);
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
