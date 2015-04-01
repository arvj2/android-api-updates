package com.jvra.transitions;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( null ==savedInstanceState ){
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace( R.id.parent, new TransitionsFragment() );
            trans.commit();
        }
    }
}
