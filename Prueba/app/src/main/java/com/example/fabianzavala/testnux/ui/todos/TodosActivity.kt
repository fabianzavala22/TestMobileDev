/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to set posts in recyclerview
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.todos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.adapters.TodosAdapter
import com.example.fabianzavala.testnux.databinding.ActivityTodosBinding
import com.example.fabianzavala.testnux.models.Todos
import com.example.fabianzavala.testnux.webservice.APIUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TodosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodosBinding

    @Inject lateinit var todosVM: TodosViewModel

    private lateinit var adapter: TodosAdapter
    private val taskData = mutableListOf<Todos>()

    val REQUEST_NEWTASK = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityTodosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_TodosActivity)

        val userId: Int = intent.extras!!.getInt("userId")
        MyApplication.userId = userId
        initRecyclerView()

        binding.loading.visibility = View.VISIBLE
        getAllTodos(userId)

        binding.fab.setOnClickListener {
            val intentTask = Intent(this, NewTaskActivity::class.java)
            intentTask.putExtra("userId", userId)
            startActivityForResult(intentTask, REQUEST_NEWTASK)
        }

        binding.refreshTodos.setOnRefreshListener {
            getAllTodos(userId)
        }
    }

    private fun initRecyclerView()
    {
        adapter = TodosAdapter(taskData)
        binding.todosRv.adapter = adapter
    }

    private fun getAllTodos(userId: Int)
    {
        APIUsers.usersService.getTodosByUserId(userId).enqueue(object : Callback<List<Todos>> {
            override fun onResponse(call: Call<List<Todos>>, response: Response<List<Todos>>) {

                binding.loading.visibility = View.GONE
                binding.refreshTodos.isRefreshing = false

                if (response.isSuccessful) {

                    val todosData: List<Todos> = response.body()?: emptyList()
                    taskData.clear()
                    taskData.addAll(todosData)
                    taskData.sortByDescending { it.id }
                    adapter.notifyDataSetChanged()
                    todosVM.insertTodos(todosData)
                }
            }

            override fun onFailure(call: Call<List<Todos>>, t: Throwable) {

                binding.loading.visibility = View.GONE
                binding.refreshTodos.isRefreshing = false

                //get todos offline
                getOfflineTodos()

                Toast.makeText(
                        applicationContext, getString(R.string.networkError),
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun getOfflineTodos(){
        todosVM.todos.observe(this, Observer {

            var todosAdapter: MutableList<Todos> = mutableListOf()

            for (todo in it) {
                val todoAdapter = Todos(
                        userId     =  todo.userId,
                        id         =  todo.id,
                        title      =  todo.title,
                        completed  =  todo.completed,
                )

                todosAdapter.add(todoAdapter)
            }

            taskData.clear()
            taskData.addAll(todosAdapter)
            taskData.sortByDescending { it.id }
            adapter.notifyDataSetChanged()

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQUEST_NEWTASK -> {
                    val newTask = data?.getSerializableExtra("newTask") as Todos
                    taskData.add(newTask)
                    todosVM.insert(newTask)
                    taskData.sortByDescending { it.id }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}