package com.jvra.api41;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/19/2015.
 */
public class UserProvider extends ContentProvider{

    @Override
    public boolean onCreate() {
        Log.e( "***********","onCreate" );
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Log.e( "***********","query" );
        return null;
    }

    @Override
    public String getType(Uri uri) {
        Log.e( "***********","getType" );
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.e( "***********","insert" );

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        Log.e( "***********","delete" );
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        Log.e( "***********","update" );
        return 0;
    }

    public String getUser(String param, Bundle extra){
        return ""+param+" extra:"+(null != extra );
    }
}
