package com.poc.coroutine_retrofit_example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.coroutine_retrofit_example.model.repository.PostRepository
import com.poc.coroutine_retrofit_example.model.response.Post
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private var postRepository: PostRepository = PostRepository()

    var mutablePostLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            try {
                val response = postRepository.getPost()
                mutablePostLiveData.postValue(response)
            } catch (ex: Exception) {
                errorLiveData.postValue(ex.toString())
                Log.d("PostViewModel", ex.toString())
            }

        }
    }
}