/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implements the users Database in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.database.users

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fabianzavala.testnux.Address
import com.example.fabianzavala.testnux.Company

@Entity(tableName = "users_table")
data class UsersRoom (

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "user_id")
        val id       : Int,

        @ColumnInfo(name = "name")
        val name     : String,

        @ColumnInfo(name = "username")
        val username : String,

        @ColumnInfo(name = "email")
        val email    : String,

        @Embedded
        val address  : Address,

        @ColumnInfo(name = "phone")
        val phone    : String,

        @ColumnInfo(name = "website")
        val website  : String,

        @Embedded(prefix = "com_")
        val company  : Company

        )
