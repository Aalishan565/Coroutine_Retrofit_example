package com.poc.coroutine_retrofit_example.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.coroutine_retrofit_example.model.repository.PostRepository
import com.poc.coroutine_retrofit_example.model.response.Post
import com.poc.coroutine_retrofit_example.network.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel : ViewModel() {

    private var postRepository: PostRepository = PostRepository()

    var mutablePostLiveData: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            try {
                mutablePostLiveData.postValue(Resource.Loading())
                val response = postRepository.getPost()
                mutablePostLiveData.postValue(handleResponse(response))
            } catch (ex: Exception) {
                handleErrorResponse(ex)
            }
        }
    }

    private fun handleErrorResponse(ex: Exception): Resource<List<Post>> {
        Log.d("PostViewModel", ex.toString())
        Log.d("PostViewModel", "${ex.message} 2")
        return ex.message?.let { Resource.Error(it) }!!
    }

    private fun handleResponse(response: Response<List<Post>>): Resource<List<Post>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        if (response.code() == 404)
            Log.d("PostViewModel", "$response 1")
        return Resource.Error("Page not found")
    }
}