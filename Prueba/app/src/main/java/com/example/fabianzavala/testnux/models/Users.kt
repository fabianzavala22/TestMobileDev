/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class based on API REST results.
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux

import androidx.room.Embedded
import java.io.Serializable

data class Users (

    val id       : Int,
    val name     : String,
    val username : String,
    val email    : String,
    val address  : Address,
    val phone    : String,
    val website  : String,
    val company  : Company

    ): Serializable

data class Address (

    val street  : String,
    val suite   : String,
    val city    : String,
    val zipcode : String,
    @Embedded
    val geo     : Geo

    ): Serializable

data class Geo(

    val lat: String,
    val lng: String

    ): Serializable

data class Company(
     val name        : String,
     val catchPhrase : String,
     val bs          : String
): Serializable
