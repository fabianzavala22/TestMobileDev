/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the post adapter
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fabianzavala.testnux.ui.comments.CommentsActivity
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.databinding.PostsAdapterBinding
import com.example.fabianzavala.testnux.models.Posts

class PostsAdapter(private val posts: List<Posts>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.posts_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val post = posts[position]
        holder.bind(post)

        holder.itemView.setOnClickListener {

            val intentComment = Intent(holder.itemView.context, CommentsActivity::class.java)
            intentComment.putExtra("postId", post.id)
            holder.itemView.context.startActivity(intentComment)
        }
    }

    override fun getItemCount(): Int = posts.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = PostsAdapterBinding.bind(view)

        fun bind(post: Posts) {

            binding.titleTv.text =  post.title
            binding.bodyTv.text  =  post.body
        }
    }
}