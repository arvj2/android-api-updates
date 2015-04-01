package com.jvra.tt;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_box);
    }

    public void onClick( View view ){

        Slide v = new Slide(Gravity.BOTTOM);
        v.setMode( Slide.MODE_OUT );

        TransitionManager.beginDelayedTransition((ViewGroup) view.getParent(), v);
        View v1 = findViewById( R.id.first );
        View v2 = findViewById( R.id.second );
        View v3 = findViewById( R.id.thrid );
        View v4 = findViewById( R.id.four );
        toggleVisibility(v1,v2,v3,v4);
    }

    private void toggleVisibility( View...views ){
        for( View v : views ){
            int vis = v.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE;
            v.setVisibility( vis );
        }
    }
}
