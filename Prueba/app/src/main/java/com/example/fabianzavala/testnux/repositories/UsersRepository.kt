/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set users data from Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.repositories

import androidx.lifecycle.LiveData
import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.database.users.UsersDAO
import com.example.fabianzavala.testnux.database.users.UsersRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersRepository @Inject constructor(private val dao: UsersDAO) {

    val users: LiveData<List<UsersRoom>> = dao.getAll()
    private val userScope = CoroutineScope(Job() + Dispatchers.IO)

    fun insertUsers(users: List<Users>) {

        var usersRoom: MutableList<UsersRoom> = mutableListOf()

        for (user in users) {
            val userRoom = UsersRoom(
                    id =       user.id,
                    name =     user.name,
                    username = user.username,
                    email =    user.email,
                    address =  user.address,
                    phone =    user.phone,
                    website =  user.website,
                    company =  user.company
            )
            usersRoom.add(userRoom)
        }

        userScope.launch {
            dao.insertAll(usersRoom)
        }
    }

    suspend fun updateUsers(users: List<UsersRoom>): Int {
        return dao.updateAll(users)
    }
}