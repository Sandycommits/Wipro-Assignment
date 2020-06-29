package com.wipro.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL: String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    fun getClient(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}