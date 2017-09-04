package com.gnardini.tvshowcontroller.utils

import com.gnardini.tvshowcontroller.networking.ControllerService
import com.gnardini.tvshowcontroller.networking.EmptyCallback
import com.gnardini.tvshowcontroller.networking.MouseService

class ControlsInteractions(
        private val controllerService: ControllerService,
        private val mouseService: MouseService) {

    fun maximize() {
        controllerService
                .maximize()
                .enqueue(EmptyCallback<String>())
    }

    fun playOrPause() {
        controllerService
                .playOrPause()
                .enqueue(EmptyCallback<String>())
    }

    fun volumeUp() {
        controllerService
                .setVolume("up")
                .enqueue(EmptyCallback<String>())
    }

    fun volumeDown() {
        controllerService
                .setVolume("down")
                .enqueue(EmptyCallback<String>())
    }

    fun rewind() {
        controllerService
                .rewind()
                .enqueue(EmptyCallback<String>())
    }

    fun fastForward() {
        controllerService
                .fastForward()
                .enqueue(EmptyCallback<String>())
    }

    fun moveMouse(x: Float, y: Float) {
        mouseService
                .moveMouse(x, y)
                .enqueue(EmptyCallback<String>())
    }

    fun leftClick() {
        mouseService
                .leftClick()
                .enqueue(EmptyCallback<String>())
    }

    fun rightClick() {
        mouseService
                .rightClick()
                .enqueue(EmptyCallback<String>())
    }

}