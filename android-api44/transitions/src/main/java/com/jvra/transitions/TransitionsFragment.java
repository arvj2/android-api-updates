package com.jvra.transitions;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/25/2015.
 */
public class TransitionsFragment extends Fragment{

    private ViewGroup root;
    private View firstView;
    private boolean reverse = true;
    private Transition transition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_main,container,false );
        root = (ViewGroup) view.findViewById( R.id.scene_root );

        firstView  = root.findViewById( R.id.container );

        view.findViewById( R.id.start_button ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                begin(view);
            }
        });

        transition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.transform);
        return view;
    }

    public void begin( View v ){
        reverse ^= true;
        Scene scene;
        if(  !reverse ) {
            scene = Scene.getSceneForLayout(root, R.layout.scene2,getActivity() );
            TransitionManager.go(scene,transition);
        }else{
            scene = new Scene(root,firstView );
            TransitionManager tManager = TransitionInflater.from(getActivity()).inflateTransitionManager(R.transition.transform_manager,root);
            tManager.transitionTo(scene);
        }
    }
}
