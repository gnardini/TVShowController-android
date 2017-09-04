package com.gnardini.tvshowcontroller.mouse_movement

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.gnardini.tvshowcontroller.R
import com.gnardini.tvshowcontroller.TvShowControllerApp
import com.gnardini.tvshowcontroller.extensions.setClickListener
import com.gnardini.tvshowcontroller.extensions.setOnTouchListener

class MouseActivity : AppCompatActivity() {

    private val commonInjector = TvShowControllerApp.commonInjector

    private val controllerInteractor = commonInjector.controllerInteractors
    private val mouseMovementTracker = commonInjector.mouseMovementTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mouse_activity)

        setOnTouchListener(R.id.mouse_input, this::onMouseMoved)
        setClickListener(R.id.left_click, controllerInteractor::leftClick)
        setClickListener(R.id.right_click, controllerInteractor::rightClick)
    }

    private fun onMouseMoved(motionEvent: MotionEvent) {
        val x = motionEvent.x
        val y = motionEvent.y
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN-> mouseMovementTracker.startMovement(x, y)
            MotionEvent.ACTION_UP -> mouseMovementTracker.endMovement(x, y)
            MotionEvent.ACTION_MOVE -> mouseMovementTracker.onMovement(x, y)
        }
    }

}
