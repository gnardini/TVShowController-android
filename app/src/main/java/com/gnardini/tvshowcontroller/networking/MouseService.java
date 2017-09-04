package com.gnardini.tvshowcontroller.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MouseService {

    @GET("/move_mouse")
    Call<String> moveMouse(@Query("x") float x, @Query("y") float y);

    @GET("/left_click")
    Call<String> leftClick();

    @GET("/right_click")
    Call<String> rightClick();

}
