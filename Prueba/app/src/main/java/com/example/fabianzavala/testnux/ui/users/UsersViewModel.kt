/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set users by repository
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.database.users.UsersRoom
import com.example.fabianzavala.testnux.repositories.UsersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val repository: UsersRepository): ViewModel() {

    val users: LiveData<List<UsersRoom>> = repository.users

    fun insertUsers(users: List<Users>) {
        repository.insertUsers(users)
    }

    fun updateUsers(users: List<UsersRoom>) = viewModelScope.launch{
        val newRowId = repository.updateUsers(users)
        if (newRowId > -1)
        {
            Log.e("User Room","UPDATED OK")
        } else
        {
            Log.e("User Room","Error Ocurred")
        }
    }
}