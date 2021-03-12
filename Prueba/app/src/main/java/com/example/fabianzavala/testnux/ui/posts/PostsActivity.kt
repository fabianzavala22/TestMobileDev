/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to set posts in recyclerview
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.adapters.PostsAdapter
import com.example.fabianzavala.testnux.databinding.ActivityPostsBinding
import com.example.fabianzavala.testnux.models.Posts
import com.example.fabianzavala.testnux.webservice.APIUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding

    private lateinit var adapter: PostsAdapter
    private val postsData = mutableListOf<Posts>()

    @Inject lateinit var postsVM: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_PostActivity)

        val userId: Int = intent.extras!!.getInt("userId")
        MyApplication.userId = userId
        initRecyclerView()

        binding.loading.visibility = View.VISIBLE
        getAllPosts(userId)

        binding.refreshPosts.setOnRefreshListener {
            getAllPosts(userId)
        }
    }

    private fun initRecyclerView()
    {
        adapter = PostsAdapter(postsData)
        binding.postsRv.adapter = adapter
    }

    private fun getAllPosts(userId: Int) {

        APIUsers.usersService.getPostsByUserId(userId).enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                binding.loading.visibility = View.GONE
                binding.refreshPosts.isRefreshing = false

                if (response.isSuccessful) {

                    val posts: List<Posts> = response.body()?: emptyList()
                    postsData.clear()
                    postsData.addAll(posts)
                    adapter.notifyDataSetChanged()
                    postsVM.insertPosts(posts)
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                binding.loading.visibility = View.GONE
                binding.refreshPosts.isRefreshing = false

                //get users offline
                getOfflinePosts()

                Toast.makeText(
                        applicationContext, getString(R.string.networkError),
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun getOfflinePosts(){
        postsVM.posts.observe(this, Observer {

            var postsAdapter: MutableList<Posts> = mutableListOf()

            for (post in it) {
                val postAdapter = Posts(
                        userId =  post.userId,
                        id     =  post.id,
                        title  =  post.title,
                        body   =  post.body
                )

                postsAdapter.add(postAdapter)
            }

            postsData.clear()
            postsData.addAll(postsAdapter)
            adapter.notifyDataSetChanged()

        })
    }
}