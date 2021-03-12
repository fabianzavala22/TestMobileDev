/********************************************************************
** -----------------------------------------------------------------------------------------------
** Description: Class to define the states of the ui form
**
** Change Log:
** Version      Date                Programmer               Description
** ----------      ---------------      ------------------------      ----------------------------
** 1.0             2021-03-09     Fabian ZV                   Created
** -----------------------------------------------------------------------------------------------
********************************************************************/

package com.example.fabianzavala.testnux.ui.login

/*
* Data validation state of the ui form.
*/

data class LoginFormState (

    val usernameError: Int?  = null,
    val passwordError: Int?  = null,
    val isDataValid: Boolean = false
)