/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implements the todos Database in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.todos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table")
data class TodosRoom (

        @ColumnInfo(name = "user_id")
        val userId    : Int,

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "todo_id")
        val id        : Int,

        @ColumnInfo(name = "title")
        val title     : String,

        @ColumnInfo(name = "completed")
        val completed : Boolean

        )
