package com.jvra.api41.ap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/19/2015.
 */
public class FourthActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);
    }

    @Override
    public boolean onNavigateUp() {
        Log.e( "****","Camming from child");
        return true;
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        Log.e( "****","Camming from "+child );
        return true;
    }
}
