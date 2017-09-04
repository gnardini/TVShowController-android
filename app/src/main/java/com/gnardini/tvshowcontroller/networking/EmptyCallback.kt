package com.gnardini.tvshowcontroller.networking

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmptyCallback<Type> : Callback<Type> {

    override fun onResponse(call: Call<Type>, response: Response<Type>) {}

    override fun onFailure(call: Call<Type>, t: Throwable) {}

}
