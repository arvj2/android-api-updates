package com.jvra.api41.consumer;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call( View v ){
        ContentProviderClient client;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ){
            client = getContentResolver().acquireUnstableContentProviderClient(Uri.parse("content://com.jvra.api41.provider1"));
            Log.e( "********","unstable" );
        }else{
            Log.e( "********","stable" );
            client = getContentResolver().acquireContentProviderClient(Uri.parse("content://com.jvra.api41.provider1"));
        }


        if( null != client){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ){
                try{
                    Log.e( "9******RESULT",""+client.call("getUser","first",new Bundle()) );
                }catch( Exception ex){
                    Log.e("********","error",ex);
                    ex.printStackTrace();
                }
                try {
                    client.insert(null, null);
                }catch ( DeadObjectException ex){
                    ex.printStackTrace();
                }catch ( Exception ex ){
                    ex.printStackTrace();
                }
            }else{
                 try {
                     client.insert(null, null);
                 }catch ( RemoteException ex){
                     ex.printStackTrace();
                 }
            }
        }else{
            Log.e( "*******","null client" );
        }
    }
}
