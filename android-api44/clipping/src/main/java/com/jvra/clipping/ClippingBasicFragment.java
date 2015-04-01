package com.jvra.clipping;

import android.app.Fragment;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class ClippingBasicFragment extends Fragment{

    private int clickCount = 0;

    private ViewOutlineProvider provider;

    private String[] sampleTexts;

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        provider = new ClipOutlineProvider();
        sampleTexts = getResources().getStringArray(R.array.sample_texts);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate( R.layout.clipping_basic_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        textView = (TextView) view.findViewById( R.id.text_view );
        changeText();

        final View clippedView = view.findViewById( R.id.frame );
        clippedView.setOutlineProvider( provider );

        view.findViewById( R.id.button ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( clippedView.getClipToOutline() ){
                    clippedView.setClipToOutline(false);
                    ((Button) view).setText(R.string.clip_button);
                }else{
                    clippedView.setClipToOutline(true);
                    ((Button) view).setText(R.string.unclip_button);
                }
            }
        });

        /* When the text is clicked, a new string is shown. */
        view.findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount++;

                // Update the text in the TextView
                changeText();

                // Invalidate the outline just in case the TextView changed size
                clippedView.invalidateOutline();
            }
        });
    }

    private void changeText(){
        String newText = sampleTexts[clickCount % sampleTexts.length];
        textView.setText(newText);
    }

    private class ClipOutlineProvider extends ViewOutlineProvider{
        @Override
        public void getOutline(View view, Outline outline) {
            final int margin = Math.min( view.getWidth(),view.getHeight())/10;
            outline.setRoundRect(margin,margin,view.getWidth()-margin,view.getHeight()-margin,margin/2);
        }
    }
}
