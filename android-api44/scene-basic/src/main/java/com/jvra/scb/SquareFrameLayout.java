package com.jvra.scb;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/24/2015.
 */
public class SquareFrameLayout extends FrameLayout{
    public SquareFrameLayout(Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize( widthMeasureSpec );
        final int heightSize = MeasureSpec.getSize( heightMeasureSpec );

        if( 0 == widthSize && 0 == heightSize ){
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
            final int minSize = Math.min( getMeasuredWidth(), getMeasuredHeight() );
            setMeasuredDimension( minSize,minSize );
            return;
        }

        final int size;
        if( 0 == widthSize || 0 == heightSize ){
            size = Math.max( widthSize,heightSize );
        }else{
            size = Math.min(widthSize,heightSize);
        }

        final int newMeasuredDimention = MeasureSpec.makeMeasureSpec(size,MeasureSpec.EXACTLY);
        super.onMeasure(newMeasuredDimention,newMeasuredDimention);
    }
}
