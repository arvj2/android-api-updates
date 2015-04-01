package com.jvra.scb;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.picasso.Picasso;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/24/2015.
 */
public class DetailActivity extends Activity {

    public static final String EXTRA_PARAM_ID = "detail:_id";

    public static final String VIEW_NAME_HEADER_IMAGE = "defail:header:image";

    public static final String VIEW_NAME_HEADER_TITLE = "defail:header:title";


    @InjectView( R.id.imageview_header )
    ImageView header;

    @InjectView( R.id.textview_title )
    TextView title;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.details );

        ButterKnife.inject(this);

        item = Item.getItem( getIntent().getIntExtra(EXTRA_PARAM_ID,0));

        ViewCompat.setTransitionName(header,VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(title,VIEW_NAME_HEADER_TITLE);

        loadItem();
    }

    private void loadItem(){
        title.setText( getString(R.string.image_header, item.getName(),item.getAuthor()));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener() ){
            loadThumbnail();
        }else{
            loadFullSizeImage();
        }
    }

    private boolean addTransitionListener(){
        final Transition transition = getWindow().getSharedElementEnterTransition();
        if( null != transition ){
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    loadFullSizeImage();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
            return true;
        }
        return false;
    }

    private void loadThumbnail(){
        Picasso.with(header.getContext())
                .load(item.getThumbnailUrl())
                .noFade()
                .into(header);
    }

    private void loadFullSizeImage(){
        Picasso.with(header.getContext())
                .load(item.getPhotoUrl())
                .noFade()
                .noPlaceholder()
                .into(header);
    }
}




