package com.jvra.fb;

import android.content.Context;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Created by Jansel R. Abreu on 3/31/2015.
 */
public class FloatingActionButton extends FrameLayout implements Checkable{


    private OnCheckedChangeListener listener = null;

    private boolean checked;

    private int[] CHECKED_STATE={
          android.R.attr.state_checked
    };

    public FloatingActionButton(Context context) {
        this(context, null, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        setClickable(true);
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,getWidth(),getHeight());
            }
        });

        setClipToOutline(true);
    }

    @Override
    public void setChecked(boolean b) {
        if( b == checked )
            return;
        checked = b;
        refreshDrawableState();

        if( null != listener )
            listener.onCheckedChanged(this,b);
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        invalidateOutline();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] state = super.onCreateDrawableState(extraSpace+1);
        if( isChecked() )
            mergeDrawableStates(state,CHECKED_STATE);
        return state;
    }

    public static abstract interface OnCheckedChangeListener{
        void onCheckedChanged(FloatingActionButton fabView, boolean isChecked);
    }
}
