/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get users Database Instance in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.users

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersRoom::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract val usersDAO: UsersDAO

    companion object{

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            UsersDatabase::class.java,
                            "user_history_database"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
                return instance
            }
        }
    }
}