/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get comments Database Instance in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.comments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CommentsRoom::class], version = 1)
abstract class CommentsDatabase: RoomDatabase() {

    abstract val commentsDAO: CommentsDAO

    companion object{

        @Volatile
        private var INSTANCE: CommentsDatabase? = null

        fun getInstance(context: Context): CommentsDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            CommentsDatabase::class.java,
                            "comments_history_database"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
                return instance
            }
        }
    }
}