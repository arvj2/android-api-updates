package com.jvra.api43;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewOverlay;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void remove( View v ){
        View text = findViewById( android.R.id.text1 );
        ViewGroup parent = (ViewGroup) text.getParent();
        parent.getOverlay().add( text );

        ObjectAnimator anim = ObjectAnimator.ofFloat(text,"translationX", parent.getRight());
        anim.setDuration(500);
        anim.start();
    }
}
