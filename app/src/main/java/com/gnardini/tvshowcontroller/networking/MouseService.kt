package com.gnardini.tvshowcontroller.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MouseService {

    @GET("/move_mouse")
    fun moveMouse(@Query("x") x: Float, @Query("y") y: Float): Call<String>

    @GET("/left_click")
    fun leftClick(): Call<String>

    @GET("/right_click")
    fun rightClick(): Call<String>

}
