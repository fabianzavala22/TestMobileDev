/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set todos data from Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.repositories

import androidx.lifecycle.LiveData
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.database.comments.CommentsRoom
import com.example.fabianzavala.testnux.database.todos.TodosDAO
import com.example.fabianzavala.testnux.database.todos.TodosRoom
import com.example.fabianzavala.testnux.models.Comments
import com.example.fabianzavala.testnux.models.Todos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodosRepository @Inject constructor(private val dao: TodosDAO) {

    val todos: LiveData<List<TodosRoom>> = dao.getTodos(MyApplication.userId)
    private val todosScope = CoroutineScope(Job() + Dispatchers.IO)

    fun insertTodos(todos: List<Todos>) {

        var todosRoom: MutableList<TodosRoom> = mutableListOf()

        for (todo in todos) {
            val todoRoom = TodosRoom(
                    userId     =  todo.userId,
                    id         =  todo.id,
                    title      =  todo.title,
                    completed  =  todo.completed,
            )
            todosRoom.add(todoRoom)
        }

        todosScope.launch {
            dao.insertAll(todosRoom)
        }
    }

    fun insert(todo: Todos) {

        val todoRoom = TodosRoom(
                    userId     =  todo.userId,
                    id         =  todo.id,
                    title      =  todo.title,
                    completed  =  todo.completed,
            )

        todosScope.launch {
            dao.insert(todoRoom)
        }
    }

    suspend fun updateTodos(todos: List<TodosRoom>): Int {
        return dao.updateAll(todos)
    }
}