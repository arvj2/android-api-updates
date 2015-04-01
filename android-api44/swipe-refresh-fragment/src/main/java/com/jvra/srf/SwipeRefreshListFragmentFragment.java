package com.jvra.srf;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class SwipeRefreshListFragmentFragment extends Fragment{

    private static final int LIST_ITEM_COUNT = 20;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        /**
         * From here all is the same as swipe-refresh-basic module
         */
    }


}
