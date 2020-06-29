package com.wipro.network

import com.wipro.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("facts.json")
    fun fetchData(): Call<ResponseData>
}