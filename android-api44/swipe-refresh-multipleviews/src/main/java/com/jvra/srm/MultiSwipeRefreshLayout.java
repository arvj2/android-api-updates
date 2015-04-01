package com.jvra.srm;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class MultiSwipeRefreshLayout extends SwipeRefreshLayout{

    private View[] mSwipeableChildren;

    public MultiSwipeRefreshLayout(Context context) {
        super(context);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeableChildren(final int... ids) {
        assert ids != null;

        // Iterate through the ids and find the Views
        mSwipeableChildren = new View[ids.length];
        for (int i = 0; i < ids.length; i++) {
            mSwipeableChildren[i] = findViewById(ids[i]);
        }
    }

    @Override
    public boolean canChildScrollUp() {
        if( null != mSwipeableChildren && mSwipeableChildren.length > 0){
            for( View view : mSwipeableChildren ){
                if(  null != view && view.isShown() && !canChildScrollUp(view))
                    return false;
            }
        }
        return true;
    }

    private boolean canChildScrollUp(View v){
        if(Build.VERSION.SDK_INT >= 14 )
            return ViewCompat.canScrollVertically(v,-1);
        else{
            if( v instanceof ListView ){
                final AbsListView list = ( AbsListView )v;
                return list.getChildCount() >0 && ( list.getFirstVisiblePosition() >0 || list.getChildAt(0).getTop() < list.getPaddingTop());
            }else
                return getRootView().getScrollY() > 0;
        }
    }
}
