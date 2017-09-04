package com.gnardini.tvshowcontroller.mouse_movement

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gnardini.tvshowcontroller.R
import com.gnardini.tvshowcontroller.extensions.setClickListener
import com.gnardini.tvshowcontroller.extensions.setOnTouchListener

class MouseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mouse_activity)

        setOnTouchListener(R.id.mouse_input, this::onMouseMoved)
        setClickListener(R.id.left_click, this::onLeftClick)
        setClickListener(R.id.right_click, this::onRightClick)
    }

    private fun onMouseMoved(x: Float, y: Float) {

    }

    private fun onLeftClick() {

    }

    private fun onRightClick() {

    }

}
