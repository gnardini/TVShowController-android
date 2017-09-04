package com.gnardini.tvshowcontroller.extensions

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gnardini.tvshowcontroller.view_helpers.ClickListener
import com.gnardini.tvshowcontroller.view_helpers.TouchListener

fun AppCompatActivity.setClickListener(id: Int, listener: ClickListener) {
    findViewById<View>(id).setOnClickListener { _ -> listener.invoke() }
}

fun AppCompatActivity.setOnTouchListener(id: Int, listener: TouchListener) {
    findViewById<View>(id).setOnTouchListener { _, motionEvent ->
        listener.invoke(motionEvent.x, motionEvent.y)
        false
    }
}
