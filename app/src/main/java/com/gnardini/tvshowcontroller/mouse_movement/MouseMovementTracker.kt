package com.gnardini.tvshowcontroller.mouse_movement

import com.gnardini.tvshowcontroller.networking.EmptyCallback
import com.gnardini.tvshowcontroller.networking.MouseService

class MouseMovementTracker(private val mouseService: MouseService) {

    private var startX: Float = 0f
    private var startY: Float = 0f

    private var lastSend: Long = -1L

    fun startMovement(x: Float, y: Float) {
        this.startX = x
        this.startY = y
        lastSend = System.currentTimeMillis()
    }

    fun onMovement(x: Float, y: Float) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastSend >= TIME_DIFF_MOVEMENTS) {
            endMovement(x, y)
        }
    }

    fun endMovement(x: Float, y: Float) {
        sendMovement(x - startX, y - startY)
        startMovement(x, y)
    }

    fun sendMovement(xDiff: Float, yDiff: Float) {
        mouseService
                .moveMouse(xDiff, yDiff)
                .enqueue(EmptyCallback<String>())
    }

    companion object {
        private val TIME_DIFF_MOVEMENTS = 20
    }

}