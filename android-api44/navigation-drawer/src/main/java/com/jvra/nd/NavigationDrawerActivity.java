package com.jvra.nd;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;

import java.util.Locale;

/**
 * Created by Jansel R. Abreu on 3/31/2015.
 */
public class NavigationDrawerActivity extends Activity implements PlanetAdapter.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle toggle;

    private CharSequence drawerTitle;
    private CharSequence title;
    private String[] planetTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_nav_drawer);

        title = drawerTitle = getTitle();
        planetTitles = getResources().getStringArray( R.array.planets_array );
        drawerLayout = (DrawerLayout) findViewById( R.id.drayer_layout );
        recyclerView = (RecyclerView) findViewById( R.id.left_drawer );


        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        recyclerView.setAdapter( new PlanetAdapter( planetTitles,this ));
        getActionBar().setDisplayHomeAsUpEnabled( true );
        getActionBar().setHomeButtonEnabled(true);


        toggle = new ActionBarDrawerToggle( this,drawerLayout,R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener( toggle );
        if( savedInstanceState == null )
            selecttItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final boolean isOpen = drawerLayout.isDrawerOpen(recyclerView);
        menu.findItem( R.id.action_websearch).setVisible( !isOpen );
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( toggle.onOptionsItemSelected(item)){
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View view, int position) {
        selecttItem(position);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void selecttItem( int position ){
        Fragment fragment = PlanetFragment.newInstance(position);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        setTitle(planetTitles[position]);
        drawerLayout.closeDrawer(recyclerView);
    }


    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        public static Fragment newInstance(int position) {
            Fragment fragment = new PlanetFragment();
            Bundle args = new Bundle();
            args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ImageView iv = ((ImageView) rootView.findViewById(R.id.image));
            iv.setImageResource(imageId);

            getActivity().setTitle(planet);
            return rootView;
        }
    }
}
