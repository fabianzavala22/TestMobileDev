/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get posts in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.posts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostsRoom>)

    @Update
    suspend fun updateAll(posts: List<PostsRoom>) : Int

    @Query("SELECT * FROM posts_table WHERE user_id = :key")
    fun getPost(key: Int): LiveData<List<PostsRoom>>

    @Query("SELECT * FROM posts_table")
    fun getAll(): LiveData<List<PostsRoom>>
}