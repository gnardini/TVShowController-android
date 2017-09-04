package com.gnardini.tvshowcontroller.directories

import com.gnardini.tvshowcontroller.networking.ControllerService
import com.gnardini.tvshowcontroller.networking.EmptyCallback

class ControlsInteractions(private val controllerService: ControllerService) {

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

}