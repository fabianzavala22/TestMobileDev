/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set posts by repository
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianzavala.testnux.database.posts.PostsRoom
import com.example.fabianzavala.testnux.models.Posts
import com.example.fabianzavala.testnux.repositories.PostsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: PostsRepository): ViewModel() {

    val posts: LiveData<List<PostsRoom>> = repository.posts

    fun insertPosts(posts: List<Posts>) {
        repository.insertPosts(posts)
    }

    fun updatePosts(posts: List<PostsRoom>) = viewModelScope.launch{
        val newRowId = repository.updatePosts(posts)
        if (newRowId > -1)
        {
            Log.e("Posts Room","UPDATED OK")
        } else
        {
            Log.e("Posts Room","Error Ocurred")
        }
    }
}