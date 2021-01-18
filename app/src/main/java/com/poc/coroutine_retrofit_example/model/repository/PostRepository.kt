package com.poc.coroutine_retrofit_example.model.repository

import com.poc.coroutine_retrofit_example.model.response.Post
import com.poc.coroutine_retrofit_example.network.CommunicationManager
import retrofit2.Response

class PostRepository {
    suspend fun getPost(): Response<List<Post>> = CommunicationManager.retrofitApi.getPost()
}