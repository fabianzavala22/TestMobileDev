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

package com.example.fabianzavala.testnux.models

data class Comments (

    val postId : Int,
    val id     : Int,
    val name   : String,
    val email  : String,
    val body   : String

    )