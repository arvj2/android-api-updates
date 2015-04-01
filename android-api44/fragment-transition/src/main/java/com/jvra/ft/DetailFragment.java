package com.jvra.ft;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/26/2015.
 */
public class DetailFragment extends Fragment implements Animator.AnimatorListener {

    private static final String ARG_RESOURCE_ID = "resource_id";
    private static final String ARG_TITLE = "title";
    private static final String ARG_X = "x";
    private static final String ARG_Y = "y";
    private static final String ARG_WIDTH = "width";
    private static final String ARG_HEIGHT = "height";


    public static final DetailFragment getInstance(int resourceId, String title, int x, int y, int width, int height) {
        DetailFragment fragemnt = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESOURCE_ID, resourceId);
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_X, x);
        args.putInt(ARG_Y, y);
        args.putInt(ARG_WIDTH, width);
        args.putInt(ARG_HEIGHT, height);
        fragemnt.setArguments(args);
        return fragemnt;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FrameLayout frame = (FrameLayout) view;
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View item = inflater.inflate(R.layout.item_meat_grid, frame, false);
        bind(item);

        Bundle args = getArguments();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(args.getInt(ARG_WIDTH), args.getInt(ARG_HEIGHT));
        params.leftMargin = args.getInt(ARG_X);
        params.topMargin = args.getInt(ARG_Y);

        frame.addView(item, params);
    }

    private void bind(View item) {
        Bundle args = getArguments();
        ImageView image = (ImageView) item.findViewById(R.id.image);
        TextView text = (TextView) item.findViewById(R.id.title);

        image.setImageResource(args.getInt(ARG_RESOURCE_ID));
        text.setText(args.getString(ARG_TITLE));
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        Scene scene = Scene.getSceneForLayout((ViewGroup)getView(),R.layout.fragment_detail_content,getActivity() );
        TransitionManager.go(scene);
        bind( scene.getSceneRoot() );
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Animator animator = AnimatorInflater.loadAnimator( getActivity(), enter ? android.R.animator.fade_in : android.R.animator.fade_out );
        if( null != animator && enter )
            animator.addListener( this );
        return animator;
    }
}
