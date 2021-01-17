package com.poc.coroutine_retrofit_example.model.repository

import com.poc.coroutine_retrofit_example.model.response.Post
import com.poc.coroutine_retrofit_example.network.CommunicationManager

class PostRepository {
    suspend fun getPost(): List<Post> = CommunicationManager.retrofitApi.getPost()
}