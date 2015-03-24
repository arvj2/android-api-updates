package com.jvra.adt;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.*;
import android.view.*;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/23/2015.
 */
public class AdapterTransitionFragment extends Fragment implements Transition.TransitionListener {

    private static final int ROOT_ID = 1;

    private static final String STATE_IS_LIST_VIEW = "is_listview";

    private FrameLayout content;

    private FrameLayout cover;

    private AbsListView absListView;

    private MeatAdapter adapter;

    public static AdapterTransitionFragment getInstance() {
        return new AdapterTransitionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean isListView;
        if (null == savedInstanceState)
            isListView = true;
        else
            isListView = savedInstanceState.getBoolean(STATE_IS_LIST_VIEW);

        inflateAbsList(inflater, container, isListView);
        return inflater.inflate(R.layout.fragment_adapter_transition, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_IS_LIST_VIEW, absListView instanceof ListView);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        content = (FrameLayout) view.findViewById(R.id.content);
        cover = (FrameLayout) view.findViewById(R.id.cover);
        content.addView(absListView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_adapter_transition, menu);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (null != item) {
            if (absListView instanceof ListView) {
                item.setIcon(R.drawable.ic_action_grid);
                item.setTitle(R.string.show_as_grid);
            } else {
                item.setIcon(R.drawable.ic_action_list);
                item.setTitle(R.string.show_as_list);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle:
                toggle();
                return true;
        }
        return false;
    }

    private void toggle() {
        cover.setVisibility(View.VISIBLE);
        FrameLayout before = copyVisibleView();

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        cover.addView(before, params);

        swapAbsListView();
        getActivity().invalidateOptionsMenu();
        absListView.post(new Runnable() {
            @Override
            public void run() {
                Scene scene = new Scene(cover, copyVisibleView());
                Transition transition = new ChangeBounds();
                transition.addListener(AdapterTransitionFragment.this);
                TransitionManager.go(scene);
            }
        });
    }

    private void swapAbsListView() {
        int first = absListView.getFirstVisiblePosition();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        inflateAbsList(inflater, (ViewGroup) absListView.getParent(), absListView instanceof GridView);
        absListView.setSelection(first);
        content.removeAllViews();
        content.addView(absListView);
    }

    private FrameLayout copyVisibleView() {
        FrameLayout layout = new FrameLayout(getActivity());
        layout.setId(ROOT_ID);
        int first = absListView.getFirstVisiblePosition();
        int index = 0;
        while (true) {
            View source = absListView.getChildAt(index);
            if (null == source)
                break;
            View destination = adapter.getView(first + index, null, layout);
            destination.setId(ROOT_ID + first + index);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(source.getWidth(), source.getHeight());
            params.leftMargin = (int) source.getX();
            params.topMargin = (int) source.getY();
            layout.addView(destination, params);
            ++index;
        }
        return layout;
    }

    private void inflateAbsList(LayoutInflater inflater, ViewGroup container, boolean inflateListView) {
        if (inflateListView) {
            absListView = (AbsListView) inflater.inflate(R.layout.fragment_meat_list, container, false);
            adapter = new MeatAdapter(inflater, R.layout.item_meat_list);
        } else {
            absListView = (AbsListView) inflater.inflate(R.layout.fragment_meat_grid, container, false);
            adapter = new MeatAdapter(inflater, R.layout.item_meat_grid);
        }
        absListView.setAdapter(adapter);
        absListView.setOnItemClickListener(adapter);
    }

    @Override
    public void onTransitionStart(Transition transition) {
    }

    @Override
    public void onTransitionEnd(Transition transition) {
        cover.removeAllViews();
        cover.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
