/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implements the comments Database in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments_table")
data class CommentsRoom (

    @ColumnInfo(name = "post_id")
    val postId : Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "comment_id")
    val id     : Int,

    @ColumnInfo(name = "name")
    val name   : String,

    @ColumnInfo(name = "email")
    val email  : String,

    @ColumnInfo(name = "body")
    val body   : String
)