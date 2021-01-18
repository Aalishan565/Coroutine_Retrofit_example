package com.poc.coroutine_retrofit_example.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.poc.coroutine_retrofit_example.R
import com.poc.coroutine_retrofit_example.model.response.Post
import com.poc.coroutine_retrofit_example.network.Resource
import com.poc.coroutine_retrofit_example.view.adapter.PostAdapter
import com.poc.coroutine_retrofit_example.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvPost: RecyclerView
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        rvPost = findViewById(R.id.recyclerView)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        postViewModel.getPost()
        postViewModel.mutablePostLiveData.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    postAdapter = PostAdapter(this, response.data as ArrayList<Post>)
                    rvPost.adapter = postAdapter
                }
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    response.message?.let { message ->
                        Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE

                }
            }
        })

    }
}