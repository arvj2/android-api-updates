package com.jvra.scb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.picasso.Picasso;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.grid)
    GridView gridView;

    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
        ButterKnife.inject(this);

        gridView.setOnItemClickListener(this);
        adapter = new GridAdapter();
        gridView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Item item = (Item) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.getId());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (this,
                        new Pair<>(view.findViewById(R.id.imageview_item), DetailActivity.VIEW_NAME_HEADER_IMAGE),
                        new Pair<>(view.findViewById(R.id.textview_name), DetailActivity.VIEW_NAME_HEADER_TITLE)
                );
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    private final class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int i) {
            return Item.ITEMS[i];
        }

        @Override
        public long getItemId(int i) {
            return Item.ITEMS[i].getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (null == view)
                view = getLayoutInflater().inflate(R.layout.grid_item, viewGroup, false);

            final Item item = getItem(i);
            ImageView image = (ImageView) view.findViewById(R.id.imageview_item);

            Picasso p = new com.squareup.picasso.Picasso.Builder(MainActivity.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                }
            }).indicatorsEnabled(true).loggingEnabled(true).build();

            p.load(item.getThumbnailUrl()).into(image);

            TextView text = (TextView) view.findViewById(R.id.textview_name);
            text.setText(item.getName());
            return view;
        }
    }
}
