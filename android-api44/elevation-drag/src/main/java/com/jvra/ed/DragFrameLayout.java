package com.jvra.ed;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class DragFrameLayout extends FrameLayout{

    private List<View> dragViews;

    private ViewDragHelper dragHelper;

    private DragFrameLayoutConroller controller;

    public DragFrameLayout(Context context) {
        this(context, null, 0, 0);
    }

    public DragFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public DragFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DragFrameLayout(final Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        dragViews =  new ArrayList<>();
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View view, int i) {
                return dragViews.contains(view);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                if( null != controller )
                    controller.onDragDrop(true);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if( null != controller )
                    controller.onDragDrop(false);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int mask = ev.getActionMasked();
        if( mask == MotionEvent.ACTION_CANCEL || mask == MotionEvent.ACTION_UP ) {
            dragHelper.cancel();
            return false;
        }
        return dragHelper. shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * Adds a new {@link View} to the list of views that are draggable within the container.
     * @param dragView the {@link View} to make draggable
     */
    public void addDragView(View dragView) {
        dragViews.add(dragView);
    }

    public void setDragFrameController(DragFrameLayoutConroller dragFrameLayoutController) {
        controller = dragFrameLayoutController;
    }


    public static abstract interface DragFrameLayoutConroller{
        abstract void onDragDrop( boolean captured );
    }
}
