package com.jvra.ct;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/25/2015.
 */
public class CustomTransitionFragment extends Fragment implements View.OnClickListener {

    private static final String STATE_CURRENT_SCENE = "current_scene";
    private Scene[] scenes;
    private int currentState;
    private Transition transition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_transition, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FrameLayout container = (FrameLayout) view.findViewById(R.id.container);

        view.findViewById(R.id.show_next_scene).setOnClickListener(this);
        scenes = new Scene[]{
                Scene.getSceneForLayout(container, R.layout.scene1, getActivity()),
                Scene.getSceneForLayout(container, R.layout.scene2, getActivity()),
                Scene.getSceneForLayout(container, R.layout.scene3, getActivity()),
        };

        transition = new ChangeColor();
        TransitionManager.go(scenes[currentState % scenes.length]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_next_scene: {
                currentState = (currentState + 1) % scenes.length;
                TransitionManager.go(scenes[currentState], transition);
                break;
            }
        }
    }
}
