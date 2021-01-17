package com.poc.coroutine_retrofit_example.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.poc.coroutine_retrofit_example.R
import com.poc.coroutine_retrofit_example.model.response.Post

class PostAdapter(private val context: Context, private val postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_item, null, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var post = postList[position]
        holder.apply {
            tvId.text = "Id:- ${post.id}"
            tvTitle.text = "Name:- ${post.title}"
        }
    }

    override fun getItemCount(): Int = postList.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId = itemView.findViewById<TextView>(R.id.tvId)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    }
}