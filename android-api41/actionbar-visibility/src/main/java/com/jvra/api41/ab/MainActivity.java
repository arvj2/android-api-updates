package com.jvra.api41.ab;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void toggle( View v ){
        ActionBar actionbar = getActionBar();
        if( actionbar.isShowing() ){
            actionbar.hide();
        }else{
            actionbar.show();
        }
    }
}
