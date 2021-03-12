/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implements the posts Database in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class PostsRoom (

        @ColumnInfo(name = "user_id")
        val userId : Int,

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "post_id")
        val id     : Int,

        @ColumnInfo(name = "title")
        val title  : String,

        @ColumnInfo(name = "body")
        val body   : String

        )