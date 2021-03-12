/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the user adapter
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fabianzavala.testnux.ui.users.UserDetailActivity
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.databinding.UsersAdapterBinding

class UsersAdapter(private val users: List<Users>): RecyclerView.Adapter<UsersAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.users_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

        holder.itemView.setOnClickListener {

            val intentDetail = Intent(holder.itemView.context, UserDetailActivity::class.java)
            intentDetail.putExtra("itemDetail", user)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = users.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = UsersAdapterBinding.bind(view)

        fun bind(user: Users) {

            binding.userNameTv.text = user.name
            binding.websiteTv.text  = user.website
            binding.phoneTv.text    = user.phone
        }
    }
}