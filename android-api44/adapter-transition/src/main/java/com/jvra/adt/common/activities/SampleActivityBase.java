package com.jvra.adt.common.activities;

import android.app.Activity;
import android.os.Bundle;
import com.jvra.adt.common.log.Log;
import com.jvra.adt.common.log.LogWrapper;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/23/2015.
 */
public class SampleActivityBase extends Activity{
    public static final String TAG = "SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        initializeLogging();
    }

    /** Set up targets to receive log data */
    public void initializeLogging() {
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        Log.i(TAG, "Ready");
    }
}
