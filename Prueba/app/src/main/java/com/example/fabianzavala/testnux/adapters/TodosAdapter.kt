/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the todos adapter
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.databinding.TodosAdapterBinding
import com.example.fabianzavala.testnux.models.Todos

class TodosAdapter(private val todos: List<Todos>): RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todos_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todos = todos[position]
        holder.bind(todos)
    }

    override fun getItemCount(): Int = todos.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TodosAdapterBinding.bind(view)

        fun bind(todos: Todos) {

            binding.titleTv.text          = todos.title
            binding.idTv.text             = todos.id.toString()
            binding.completedCb.isChecked = todos.completed

        }
    }

}