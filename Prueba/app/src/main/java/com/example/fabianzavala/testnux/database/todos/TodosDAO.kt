/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get todos in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.todos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodosRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<TodosRoom>)

    @Update
    suspend fun updateAll(todos: List<TodosRoom>) : Int

    @Query("SELECT * FROM todos_table WHERE user_id = :key")
    fun getTodos(key: Int): LiveData<List<TodosRoom>>

    @Query("SELECT * FROM todos_table")
    fun getAll(): LiveData<List<TodosRoom>>
}