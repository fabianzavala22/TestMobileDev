/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get todos Database Instance in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.todos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fabianzavala.testnux.database.users.UsersDAO
import com.example.fabianzavala.testnux.database.users.UsersDatabase
import com.example.fabianzavala.testnux.database.users.UsersRoom

@Database(entities = [TodosRoom::class], version = 1)
abstract class TodosDatabase: RoomDatabase()
{
    abstract val todosDAO: TodosDAO

    companion object{

        @Volatile
        private var INSTANCE: TodosDatabase? = null

        fun getInstance(context: Context): TodosDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            TodosDatabase::class.java,
                            "todos_history_database"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
                return instance
            }
        }
    }
}