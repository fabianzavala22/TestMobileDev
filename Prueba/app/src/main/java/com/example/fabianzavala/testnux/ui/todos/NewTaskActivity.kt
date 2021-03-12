/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to add functionality to add new task
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
import android.text.TextUtils
import android.widget.Toast
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.databinding.ActivityNewTaskBinding
import com.example.fabianzavala.testnux.models.NewTask
import com.example.fabianzavala.testnux.models.Todos
import com.example.fabianzavala.testnux.webservice.APIUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTaskBinding
    //private lateinit var newTaskData: Todos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_NewTaskActivity)

        val userId: Int = intent.extras!!.getInt("userId")

        binding.saveBtn.setOnClickListener {

            if(validateForm())
            {
                saveNewTask(userId)
            }
        }
    }

    private fun validateForm(): Boolean {

        var valid = false

        val title = binding.titleEdt.text.toString()
        if (TextUtils.isEmpty(title)) {
            binding.titleEdt.error = "Campo requerido"
        } else {
            binding.titleEdt.error = null
            valid = true
        }

        return valid
    }

    private fun saveNewTask(userId: Int)
    {
        var task: NewTask = NewTask(userId = userId, title = binding.titleEdt.text.toString(),
                completed = binding.completedCb.isChecked)

        APIUsers.usersService.addTodosByUserId(userId, task).enqueue(object : Callback<Todos> {
            override fun onResponse(call: Call<Todos>, response: Response<Todos>) {

                if (response.isSuccessful) {

                    val newTaskData = response.body()!!
                    val id = response.body()!!.id
                    Toast.makeText(
                            applicationContext, "Tarea agregada correctamente id = $id",
                            Toast.LENGTH_SHORT
                    ).show()

                    val resultIntent = Intent()
                    resultIntent.putExtra("newTask", newTaskData)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Todos>, t: Throwable) {
                Toast.makeText(
                        applicationContext, getString(R.string.networkError),
                        Toast.LENGTH_SHORT
                ).show()
                finish()
            }

        })
    }
}