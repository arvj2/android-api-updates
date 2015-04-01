package com.jvra.ct;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/25/2015.
 */
public class ChangeColor extends Transition{

    private static final String PROPNAME_BACKGROUND = "ct:change_color:background";

    private void captureValues(TransitionValues values) {
        values.values.put(PROPNAME_BACKGROUND, values.view.getBackground());
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if( null == startValues || null == endValues )
            return null;

        final View view = endValues.view;

        Log.e("*******",""+(view == startValues.view)+" & "+(view.equals(startValues.view)));
        Drawable start = (Drawable) startValues.values.get( PROPNAME_BACKGROUND );
        Drawable end = ( Drawable ) endValues.values.get( PROPNAME_BACKGROUND );

        if( start instanceof ColorDrawable && end instanceof ColorDrawable ){
            ColorDrawable startColor = (ColorDrawable) start;
            ColorDrawable endColor = (ColorDrawable) end;

            if( startColor.getColor() != endColor.getColor() ){
                ValueAnimator animator  = ValueAnimator.ofArgb(startColor.getColor(),endColor.getColor());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Object value = valueAnimator.getAnimatedValue();
                        if( null != value )
                            view.setBackgroundColor( (Integer)value );
                    }
                });
                return animator;
            }
        }
        return null;
    }
}
