package com.jvra.nd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private Sample[] samples;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        samples = new Sample[]{
                new Sample(R.string.navigationdraweractivity_title, R.string.navigationdraweractivity_description,
                        NavigationDrawerActivity.class),
        };

        gridView = (GridView) findViewById( R.id.grid );
        gridView.setAdapter( new SampleAdapter() );
        gridView.setOnItemClickListener( this );
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        startActivity(samples[position].intent);
    }

    private class SampleAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return samples.length;
        }

        @Override
        public Object getItem(int position) {
            return samples[position];
        }

        @Override
        public long getItemId(int position) {
            return samples[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sample_dashboard_item,
                        container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1)).setText(
                    samples[position].titleResId);
            ((TextView) convertView.findViewById(android.R.id.text2)).setText(
                    samples[position].descriptionResId);
            return convertView;
        }
    }

    private class Sample {
        int titleResId;
        int descriptionResId;
        Intent intent;

        private Sample(int titleResId, int descriptionResId, Intent intent) {
            this.intent = intent;
            this.titleResId = titleResId;
            this.descriptionResId = descriptionResId;
        }

        private Sample(int titleResId, int descriptionResId,
                       Class<? extends Activity> activityClass) {
            this(titleResId, descriptionResId,
                    new Intent(MainActivity.this, activityClass));
        }
    }

}
