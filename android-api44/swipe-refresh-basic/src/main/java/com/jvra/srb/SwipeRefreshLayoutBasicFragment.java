package com.jvra.srb;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class SwipeRefreshLayoutBasicFragment extends Fragment {

    private static final int LIST_ITEM_COUNT = 20;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sample, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        listView = (ListView) v.findViewById(android.R.id.list);

        swipeRefreshLayout.setColorSchemeColors(R.color.swipe_color_1, R.color.swipe_color_2, R.color.swipe_color_3, R.color.swipe_color_4);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, Cheeses.randomList(LIST_ITEM_COUNT));
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                if (!swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(true);
                initiateRefresh();
                return true;
        }
        return false;
    }

    private void initiateRefresh() {
        new DummyBackgroundTask().execute();
    }

    private void onRefreshComplete(List<String> result){
        adapter.clear();
        for ( String s : result )
            adapter.add( s );
        swipeRefreshLayout.setRefreshing(false);
    }


    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected List<String> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Cheeses.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }
}
