package com.poc.coroutine_retrofit_example.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.poc.coroutine_retrofit_example.R
import com.poc.coroutine_retrofit_example.model.response.Post
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
        progressBar.visibility = View.VISIBLE
        postViewModel.getPost()
        postViewModel.mutablePostLiveData.observe(this, Observer {
            progressBar.visibility = View.GONE
            postAdapter = PostAdapter(this, it as ArrayList<Post>)
            rvPost.adapter = postAdapter
        })
        postViewModel.errorLiveData.observe(this, Observer {
            progressBar.visibility = View.GONE
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })
    }
}