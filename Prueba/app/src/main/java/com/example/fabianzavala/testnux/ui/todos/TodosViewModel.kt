/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to get and set todos by repository
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.todos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianzavala.testnux.database.todos.TodosRoom
import com.example.fabianzavala.testnux.models.Todos
import com.example.fabianzavala.testnux.repositories.TodosRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodosViewModel @Inject constructor(private val repository: TodosRepository): ViewModel() {

    val todos: LiveData<List<TodosRoom>> = repository.todos

    fun insertTodos(todos: List<Todos>) {
        repository.insertTodos(todos)
    }

    fun insert(todo: Todos) {
        repository.insert(todo)
    }

    fun updateTodos(todos: List<TodosRoom>) = viewModelScope.launch{
        val newRowId = repository.updateTodos(todos)
        if (newRowId > -1)
        {
            Log.e("Todos Room","UPDATED OK")
        } else
        {
            Log.e("Todos Room","Error Ocurred")
        }
    }
}