package com.jvra.reveal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Jansel R. Abreu on 3/31/2015.
 */
public class RevealEffectBasicFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.reveal_effect, container, false);

        View button = rootView.findViewById(R.id.button);

        // Set a listener to reveal the view when clicked.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View shape = rootView.findViewById(R.id.circle);


                Animator animator = ViewAnimationUtils.createCircularReveal(
                        shape,
                        shape.getWidth()/2,
                        shape.getHeight()/2,
                        0,
                        (float) Math.hypot(shape.getWidth(), shape.getHeight()));


                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        });

        return rootView;
    }
}
