package com.jvra.api41.ab.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/20/2015.
 */
public class ScrollContent extends ScrollView implements View.OnSystemUiVisibilityChangeListener, View.OnClickListener {

    private TextView text;
    private TextView titleView;
    private SeekBar seekView;
    boolean visible;

    private int baseSystemUIVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    private int lastSystemUI;

    private Runnable hidder = new Runnable() {
        @Override
        public void run() {
            setNavVisible(false);
        }
    };

    public ScrollContent(Context context, AttributeSet attrs) {
        super(context, attrs);

        text = new TextView(context);
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        text.setText("First text");
        text.setClickable(false);
        text.setOnClickListener(this);
        text.setTextIsSelectable(true);
        text.setBackgroundResource( android.R.color.holo_blue_light );
        addView(text, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setOnSystemUiVisibilityChangeListener(this);
    }

    public void init(TextView title, SeekBar seek) {
        titleView = title;
        seekView = seek;
        setNavVisible(true);
    }


    @Override
    public void onClick(View view) {
        int curVis = getSystemUiVisibility();
        Log.e( "**********","System:"+curVis );
        setNavVisible((curVis & SYSTEM_UI_FLAG_LOW_PROFILE) != 0);
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        int diff = lastSystemUI ^ visibility;
        Log.e( "****","onSystemUiVisibilityChange:"+visibility+", diff:"+diff );
        lastSystemUI = visibility;
        if ((diff & SYSTEM_UI_FLAG_LOW_PROFILE) != 0 && (visibility & SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
            setNavVisible(true);
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.e( "****","onWindowVisibilityChanged:"+visibility );

        setNavVisible(true);
        getHandler().postDelayed(hidder, 2000);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        Log.e("***********", String.format("l=%d,t=%d,oldl=%d,oldt=%d", l, t, oldl, oldt));

        setNavVisible(false);
    }

    public void setBaseSystemUIVisibility(int baseSystemUIVisibility) {
        this.baseSystemUIVisibility = baseSystemUIVisibility;
    }

    private void setNavVisible(boolean visible) {
        Log.e( "****","setNavVisible"+visible );
        int vis = baseSystemUIVisibility;
        if (!visible) {
            vis |= SYSTEM_UI_FLAG_LOW_PROFILE | SYSTEM_UI_FLAG_FULLSCREEN;
        }
        final boolean changedByMe = vis == getSystemUiVisibility();

        Log.e( "********","Changed:"+changedByMe );
        if (changedByMe || visible) {
            Handler h = getHandler();
            if( null != h ){
                h.removeCallbacks(hidder);
            }
        }
        setSystemUiVisibility(vis);
    }
}
