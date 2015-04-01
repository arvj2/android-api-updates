package com.jvra.ft;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/26/2015.
 */
public class FragmentTransitionFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MeatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new MeatAdapter(inflater, R.layout.item_meat_grid);
        return inflater.inflate(R.layout.fragment_transition_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView grid = (GridView) view;
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Meat meat = (Meat) adapterView.getItemAtPosition(position);
        getFragmentManager().beginTransaction()
                .replace(R.id.sample_content_fragment, DetailFragment.getInstance(
                                meat.resourceId, meat.title, (int) view.getX(), (int) view.getY(), view.getWidth(), view.getHeight())
                ).addToBackStack("detail")
                .commit();
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return AnimatorInflater.loadAnimator(getActivity(), enter ? android.R.animator.fade_in : android.R.animator.fade_out);
    }
}
