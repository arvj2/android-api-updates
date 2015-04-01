package com.jvra.bim;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/24/2015.
 */
public class BasicImmersiveModeFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View deco = getActivity().getWindow().getDecorView();
        deco.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                 int height = deco.getHeight();
                Log.e( "********","Height: " +height );
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sample_action) {
            toggleHideyBar();
        }
        return true;
    }


    private void toggleHideyBar(){
        int uiOptions = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        uiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getActivity().getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }
}
