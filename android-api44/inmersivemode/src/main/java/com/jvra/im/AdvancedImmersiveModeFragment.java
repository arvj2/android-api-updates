package com.jvra.im;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/24/2015.
 */
public class AdvancedImmersiveModeFragment extends Fragment {

    public static final String TAG = "AdvancedImmersiveModeFragment";

    @InjectView(R.id.flag_hide_navbar)
    CheckBox mHideNavCheckbox;

    @InjectView(R.id.flag_hide_statbar)
    CheckBox mHideStatusBarCheckBox;

    @InjectView(R.id.flag_enable_immersive)
    CheckBox mImmersiveModeCheckBox;

    @InjectView(R.id.flag_enable_immersive_sticky)
    CheckBox mImmersiveModeStickyCheckBox;

    @InjectView(R.id.flag_enable_lowprof)
    CheckBox mLowProfileCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_flags, container, false);
        ButterKnife.inject(this, view);

        final Button toggleFlagsButton = (Button) view.findViewById(R.id.btn_changeFlags);
        toggleFlagsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleUIFlags();
            }
        });

        final Button presetsImmersiveModeButton = (Button) view.findViewById(R.id.btn_immersive);
        presetsImmersiveModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                int uiOptions = view.getSystemUiVisibility();

                uiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
                uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
                uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
                uiOptions |= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

                view.setSystemUiVisibility(uiOptions);

                mLowProfileCheckBox.setChecked(false);
                mHideNavCheckbox.setChecked(true);
                mHideStatusBarCheckBox.setChecked(true);
                mImmersiveModeCheckBox.setChecked(true);
                mImmersiveModeStickyCheckBox.setChecked(false);

            }
        });


        final Button presetsLeanbackModeButton = (Button) view.findViewById(R.id.btn_leanback);
        presetsLeanbackModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                int uiOptions = view.getSystemUiVisibility();

                uiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
                uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
                uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                uiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE;
                uiOptions |= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

                view.setSystemUiVisibility(uiOptions);

                mLowProfileCheckBox.setChecked(false);
                mHideNavCheckbox.setChecked(true);
                mHideStatusBarCheckBox.setChecked(true);
                mImmersiveModeCheckBox.setChecked(false);
                mImmersiveModeStickyCheckBox.setChecked(false);
            }
        });

        int uiOptions = view.getSystemUiVisibility();
        uiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        uiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        uiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        view.setSystemUiVisibility(uiOptions);
        return view;
    }

    private void toggleUIFlags() {
        View decorator = getActivity().getWindow().getDecorView();
        int uiOptions = decorator.getSystemUiVisibility();
        int newUiOptions = uiOptions;

        if (mLowProfileCheckBox.isChecked())
            newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        else
            newUiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;

        // BEGIN_INCLUDE (toggle_lowprofile_mode)
        // Low profile mode doesn't resize the screen at all, but it covers the nav & status bar
        // icons with black so they're less distracting.  Unlike "full screen" and "hide nav bar,"
        // this mode doesn't interact with immersive mode at all, but it's instructive when running
        // this sample to observe the differences in behavior.
        if (mLowProfileCheckBox.isChecked()) {
            newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        } else {
            newUiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        // END_INCLUDE (toggle_lowprofile_mode)

        // BEGIN_INCLUDE (toggle_fullscreen_mode)
        // When enabled, this flag hides non-critical UI, such as the status bar,
        // which usually shows notification icons, battery life, etc
        // on phone-sized devices.  The bar reappears when the user swipes it down.  When immersive
        // mode is also enabled, the app-drawable area expands, and when the status bar is swiped
        // down, it appears semi-transparently and slides in over the app, instead of pushing it
        // down.
        if (mHideStatusBarCheckBox.isChecked()) {
            newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        } else {
            newUiOptions &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        // END_INCLUDE (toggle_fullscreen_mode)

        // BEGIN_INCLUDE (toggle_hidenav_mode)
        // When enabled, this flag hides the black nav bar along the bottom,
        // where the home/back buttons are.  The nav bar normally instantly reappears
        // when the user touches the screen.  When immersive mode is also enabled, the nav bar
        // stays hidden until the user swipes it back.
        if (mHideNavCheckbox.isChecked()) {
            newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        } else {
            newUiOptions &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        // END_INCLUDE (toggle_hidenav_mode)

        // BEGIN_INCLUDE (toggle_immersive_mode)
        // Immersive mode doesn't do anything without at least one of the previous flags
        // enabled.  When enabled, it allows the user to swipe the status and/or nav bars
        // off-screen.  When the user swipes the bars back onto the screen, the flags are cleared
        // and immersive mode is automatically disabled.
        if (mImmersiveModeCheckBox.isChecked()) {
            newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        } else {
            newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE;
        }
        // END_INCLUDE (toggle_immersive_mode)

        // BEGIN_INCLUDE (toggle_immersive_mode_sticky)
        // There's actually two forms of immersive mode, normal and "sticky".  Sticky immersive mode
        // is different in 2 key ways:
        //
        // * Uses semi-transparent bars for the nav and status bars
        // * This UI flag will *not* be cleared when the user interacts with the UI.
        //   When the user swipes, the bars will temporarily appear for a few seconds and then
        //   disappear again.
        if (mImmersiveModeStickyCheckBox.isChecked()) {
            newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        } else {
            newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        // END_INCLUDE (toggle_immersive_mode_sticky)

        // BEGIN_INCLUDE (set_ui_flags)
        //Set the new UI flags.
        decorator.setSystemUiVisibility(newUiOptions);
    }
}
