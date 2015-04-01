package com.jvra.srf;

import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class SwipeRefreshListFragment extends ListFragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = super.onCreateView(inflater, container, savedInstanceState);

        mSwipeRefreshLayout = new ListFragmentSwipeRefreshLayout(getActivity());
        mSwipeRefreshLayout.addView( parent,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSwipeRefreshLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        return mSwipeRefreshLayout;
    }

    /**
     * Set the {@link android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener} to listen for
     * initiated refreshes.
     *
     * @see android.support.v4.widget.SwipeRefreshLayout#setOnRefreshListener(android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener)
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    /**
     * Returns whether the {@link android.support.v4.widget.SwipeRefreshLayout} is currently
     * refreshing or not.
     *
     * @see android.support.v4.widget.SwipeRefreshLayout#isRefreshing()
     */
    public boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    /**
     * Set whether the {@link android.support.v4.widget.SwipeRefreshLayout} should be displaying
     * that it is refreshing or not.
     *
     * @see android.support.v4.widget.SwipeRefreshLayout#setRefreshing(boolean)
     */
    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    /**
     * Set the color scheme for the {@link android.support.v4.widget.SwipeRefreshLayout}.
     *
     * @see android.support.v4.widget.SwipeRefreshLayout#setColorScheme(int, int, int, int)
     */
    public void setColorScheme(int colorRes1, int colorRes2, int colorRes3, int colorRes4) {
        mSwipeRefreshLayout.setColorSchemeColors(colorRes1, colorRes2, colorRes3, colorRes4);
    }

    /**
     * @return the fragment's {@link android.support.v4.widget.SwipeRefreshLayout} widget.
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }


    private class ListFragmentSwipeRefreshLayout extends SwipeRefreshLayout{
        public ListFragmentSwipeRefreshLayout(Context context) {
            super(context);
        }

        @Override
        public boolean canChildScrollUp() {
            final ListView list = getListView();
            return View.VISIBLE == list.getVisibility() && SwipeRefreshListFragment.this.canChildScrollUp(list);
        }
    }

    private boolean canChildScrollUp( ListView v ){
        if(Build.VERSION.SDK_INT >= 14 )
            return ViewCompat.canScrollVertically(v,-1);
        else
            return v.getChildCount() > 0 && (v.getFirstVisiblePosition() > 0 || v.getChildAt(0).getTop() < v.getPaddingTop()) ;
    }

}
