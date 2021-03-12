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

data class Posts (
    val userId : Int,
    val id     : Int,
    val title  : String,
    val body   : String
    )
