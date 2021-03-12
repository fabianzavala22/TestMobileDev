/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get comments in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.comments

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<CommentsRoom>)

    @Update
    suspend fun updateAll(comments: List<CommentsRoom>) : Int

    @Query("SELECT * FROM comments_table WHERE post_id = :key")
    fun getComments(key: Int): LiveData<List<CommentsRoom>>

    @Query("SELECT * FROM comments_table")
    fun getAll(): LiveData<List<CommentsRoom>>
}