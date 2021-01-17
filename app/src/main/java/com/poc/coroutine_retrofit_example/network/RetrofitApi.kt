package com.poc.coroutine_retrofit_example.network

import com.poc.coroutine_retrofit_example.model.response.Post
import retrofit2.http.GET

interface RetrofitApi {

    @GET("posts")
    suspend fun getPost(): List<Post>
}