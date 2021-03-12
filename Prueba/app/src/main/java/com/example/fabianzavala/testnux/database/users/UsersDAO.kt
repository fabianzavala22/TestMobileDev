/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get users in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.users

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UsersRoom>)

    @Update
    suspend fun updateAll(employees: List<UsersRoom>) : Int

    @Query("SELECT * FROM users_table WHERE user_id = :key")
    fun getUser(key: Int): LiveData<UsersRoom>

    @Query("SELECT * FROM users_table")
    fun getAll(): LiveData<List<UsersRoom>>
}
