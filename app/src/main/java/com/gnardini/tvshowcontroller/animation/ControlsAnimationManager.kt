package com.gnardini.tvshowcontroller.animation

import android.view.View

class ControlsAnimationManager(private val controls: View, controlsSwitch: View) {

    private val originalY = controls.y
    private val controlsHeight = controls.height - controlsSwitch.height

    private var showingControls = true

    fun toggle() {
        if (showingControls) {
            hide()
        } else {
            show()
        }
    }

    fun show() {
        showingControls = true
        controls.animate()
                .y(originalY)
                .start()
    }

    fun hide() {
        showingControls = false
        controls.animate()
                .y(originalY + controlsHeight)
                .start()
    }
}
