package com.jvra.api41.ap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launch( View v){
        TaskStackBuilder builder = TaskStackBuilder.create(this);
//        builder.addNextIntent( new Intent(this,SecondActivity.class) );
//        builder.addNextIntent( new Intent(this,ThridActivity.class) );
//        builder.addNextIntent( new Intent(this,FourthActivity.class) );

        builder.addNextIntentWithParentStack( new Intent(this, FiveActivity.class));

        builder.startActivities();
    }
}
