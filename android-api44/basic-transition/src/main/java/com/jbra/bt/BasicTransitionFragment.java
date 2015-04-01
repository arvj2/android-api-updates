package com.jbra.bt;

import android.app.Fragment;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/25/2015.
 */
public class BasicTransitionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;

    private TransitionManager tManager;
    private ViewGroup root;

    public static BasicTransitionFragment newInstance() {
        return new BasicTransitionFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_transition,container,false);
        RadioGroup radioGroup = ( RadioGroup ) view.findViewById( R.id.select_scene );
        radioGroup.setOnCheckedChangeListener( this );

        root = ( ViewGroup ) view.findViewById( R.id.scene_root );

        scene1 = new Scene(root,view.findViewById( R.id.container ));
        scene2 = Scene.getSceneForLayout(root,R.layout.scene2,getActivity());
        scene3 = Scene.getSceneForLayout(root,R.layout.scene3,getActivity());

        tManager = TransitionInflater.from( getActivity() ). inflateTransitionManager(R.transition.scene_3_transition_manager,root);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch( checkedId ){
            case R.id.select_scene_1:
                TransitionManager.go( scene1 );
                break;
            case R.id.select_scene_2:
                TransitionManager.go(scene2,new AutoTransition());
                break;
            case R.id.select_scene_3:
                tManager.transitionTo(scene3);
                break;
            case R.id.select_scene_4:

                TransitionManager.beginDelayedTransition(root);
                View square = root.findViewById( R.id.transition_square );
                ViewGroup.LayoutParams params = square.getLayoutParams();
                int newSize = getResources().getDimensionPixelSize(R.dimen.square_size_expanded);
                params.width = newSize;
                params.height = newSize;
                square.setLayoutParams(params);

                break;
            default:
                break;
        }
    }
}
