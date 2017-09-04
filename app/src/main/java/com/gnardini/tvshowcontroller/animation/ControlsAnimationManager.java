package com.gnardini.tvshowcontroller.animation;

import android.view.View;

public class ControlsAnimationManager {

    private final View controls;

    private final float originalY;
    private final int controlsHeight;

    private boolean showingControls;

    public ControlsAnimationManager(View controls, View controlsSwitch) {
        this.controls = controls;
        originalY = controls.getY();
        controlsHeight = controls.getHeight() - controlsSwitch.getHeight();
        showingControls = true;
    }

    public void toggle() {
        if (showingControls) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        showingControls = true;
        controls.animate()
                .y(originalY)
                .start();
    }

    public void hide() {
        showingControls = false;
        controls.animate()
                .y(originalY + controlsHeight)
                .start();
    }
}
