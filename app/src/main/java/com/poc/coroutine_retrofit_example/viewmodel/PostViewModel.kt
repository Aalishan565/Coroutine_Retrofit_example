package com.poc.coroutine_retrofit_example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.coroutine_retrofit_example.model.repository.PostRepository
import com.poc.coroutine_retrofit_example.model.response.Post
import kotlinx.coroutines.launch

class PostViewModel() : ViewModel() {

    private var postRepository: PostRepository = PostRepository()

    var mutablePostLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = postRepository.getPost()
            mutablePostLiveData.postValue(response)
        }
    }
}