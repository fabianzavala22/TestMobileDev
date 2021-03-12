/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to set comments in recyclerview
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.comments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.adapters.CommentsAdapter
import com.example.fabianzavala.testnux.databinding.ActivityCommentsBinding
import com.example.fabianzavala.testnux.models.Comments
import com.example.fabianzavala.testnux.webservice.APIUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var adapter: CommentsAdapter
    private val commentsData = mutableListOf<Comments>()

    @Inject lateinit var commentsVM: CommentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_CommentsActivity)

        val postId: Int = intent.extras!!.getInt("postId")
        MyApplication.postId = postId
        initRecyclerView()

        binding.loading.visibility = View.VISIBLE
        getAllComments(postId)

        binding.refreshComments.setOnRefreshListener {
            getAllComments(postId)
        }

    }

    private fun initRecyclerView()
    {
        adapter = CommentsAdapter(commentsData)
        binding.commentsRv.adapter = adapter
    }

    private fun getAllComments(postId: Int)
    {
        APIUsers.usersService.getCommentsByPostId(postId).enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {

                binding.loading.visibility = View.GONE
                binding.refreshComments.isRefreshing = false

                if (response.isSuccessful) {

                    val comments: List<Comments> = response.body()?: emptyList()
                    commentsData.clear()
                    commentsData.addAll(comments)
                    adapter.notifyDataSetChanged()
                    commentsVM.insertComments(comments)
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                binding.loading.visibility = View.GONE
                binding.refreshComments.isRefreshing = false

                //get users offline
                getOfflineComments()

                Toast.makeText(
                        applicationContext, getString(R.string.networkError),
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun getOfflineComments()
    {
        commentsVM.comments.observe(this, Observer {

            var commentsAdapter: MutableList<Comments> = mutableListOf()

            for (comment in it) {
                val commentAdapter = Comments(
                        postId  =  comment.postId,
                        id      =  comment.id,
                        name    =  comment.name,
                        email   =  comment.email,
                        body    =  comment.body
                )

                commentsAdapter.add(commentAdapter)
            }

            commentsData.clear()
            commentsData.addAll(commentsAdapter)
            adapter.notifyDataSetChanged()

        })
    }
}