package com.gnardini.tvshowcontroller.injector

import com.gnardini.tvshowcontroller.mouse_movement.MouseMovementTracker
import com.gnardini.tvshowcontroller.networking.ControllerService
import com.gnardini.tvshowcontroller.networking.HostManager
import com.gnardini.tvshowcontroller.networking.MouseService
import com.gnardini.tvshowcontroller.networking.RetrofitServices
import com.gnardini.tvshowcontroller.utils.ControlsInteractions

class CommonInjector {

    val hostManager by lazy { HostManager() }
    val retrofitServices by lazy { RetrofitServices(hostManager) }
    val controllerInteractors by lazy {
        ControlsInteractions(getControllerService(), getMouseService())
    }
    val mouseMovementTracker by lazy { MouseMovementTracker(getMouseService()) }

    fun getControllerService() = retrofitServices.getService(ControllerService::class.java)
    fun getMouseService() = retrofitServices.getService(MouseService::class.java)
}
