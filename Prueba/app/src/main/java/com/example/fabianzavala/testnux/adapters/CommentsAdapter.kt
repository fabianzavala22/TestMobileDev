/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the comments adapter
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.databinding.CommentsAdapterBinding
import com.example.fabianzavala.testnux.models.Comments
import com.example.fabianzavala.testnux.models.Posts

class CommentsAdapter(private val comments: List<Comments>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.comments_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = comments.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = CommentsAdapterBinding.bind(view)

        fun bind(comment: Comments) {

            binding.nameTv.text  = comment.name
            binding.emailTv.text = comment.email
            binding.bodyTv.text  = comment.body
        }
    }
}