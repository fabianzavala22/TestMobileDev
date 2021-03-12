/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set posts data from Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.repositories

import androidx.lifecycle.LiveData
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.database.posts.PostsDAO
import com.example.fabianzavala.testnux.database.posts.PostsRoom
import com.example.fabianzavala.testnux.models.Posts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsRepository @Inject constructor(private val dao: PostsDAO) {

    val posts: LiveData<List<PostsRoom>> = dao.getPost(MyApplication.userId)
    private val postScope = CoroutineScope(Job() + Dispatchers.IO)

    fun insertPosts(posts: List<Posts>) {

        var postsRoom: MutableList<PostsRoom> = mutableListOf()

        for (post in posts) {
            val postRoom = PostsRoom(
                    userId =  post.userId,
                    id     =  post.id,
                    title  =  post.title,
                    body   =  post.body
            )
            postsRoom.add(postRoom)
        }

        postScope.launch {
            dao.insertAll(postsRoom)
        }
    }

    suspend fun updatePosts(posts: List<PostsRoom>): Int {
        return dao.updateAll(posts)
    }
}