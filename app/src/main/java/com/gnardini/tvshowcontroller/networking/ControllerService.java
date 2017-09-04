package com.gnardini.tvshowcontroller.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ControllerService {

    @GET("/fetch")
    Call<List<String>> fetch(@Query("directory") String directory);

    @GET("/maximize")
    Call<String> maximize();

    @GET("/pause")
    Call<String> playOrPause();

    @GET("/volume")
    Call<String> setVolume(@Query("direction") String direction);

    @GET("/rewind")
    Call<String> rewind();

    @GET("/fast_forward")
    Call<String> fastForward();

}
