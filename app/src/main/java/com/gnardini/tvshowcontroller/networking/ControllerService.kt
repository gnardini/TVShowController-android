package com.gnardini.tvshowcontroller.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ControllerService {

    @GET("/fetch")
    fun fetch(@Query("directory") directory: String): Call<List<String>>

    @GET("/maximize")
    fun maximize(): Call<String>

    @GET("/pause")
    fun playOrPause(): Call<String>

    @GET("/volume")
    fun setVolume(@Query("direction") direction: String): Call<String>

    @GET("/rewind")
    fun rewind(): Call<String>

    @GET("/fast_forward")
    fun fastForward(): Call<String>

}
