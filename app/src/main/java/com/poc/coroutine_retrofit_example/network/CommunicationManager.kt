package com.poc.coroutine_retrofit_example.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommunicationManager {

    val url = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }

}