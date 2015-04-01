package com.jvra.ed;

import android.app.Fragment;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/30/2015.
 */
public class ElevationDragFragment extends Fragment{

    private ViewOutlineProvider outlineProviderCircle;

    private float elevation;

    private int elevationStep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        outlineProviderCircle = new CircleOutlineProvider();
        elevationStep = getResources().getDimensionPixelSize(R.dimen.elevation_step);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ztranslation,container,false);
        final View circle = root.findViewById(R.id.circle);

        circle.setOutlineProvider( outlineProviderCircle );
        circle.setClipToOutline(true);

        DragFrameLayout dragFrameLayout = (DragFrameLayout) root.findViewById( R.id.main_layout );
        dragFrameLayout.setDragFrameController(new DragFrameLayout.DragFrameLayoutConroller() {
            @Override
            public void onDragDrop(boolean captured) {
                circle.animate().translationZ( captured ?50:0 ).setDuration(100);
            }
        });

        dragFrameLayout.addDragView(circle);
        root.findViewById(R.id.raise_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elevation += elevationStep;
                circle.setElevation(elevation);
            }
        });

        root.findViewById(R.id.lower_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elevation -= elevationStep;
                if (elevation < 0) {
                    elevation = 0;
                }
                circle.setElevation(elevation);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class CircleOutlineProvider extends ViewOutlineProvider{
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(0,0,view.getWidth(),view.getHeight());
        }
    }
}
