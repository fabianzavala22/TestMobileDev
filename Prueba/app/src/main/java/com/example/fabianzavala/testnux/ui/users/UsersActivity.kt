/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to set users in recyclerview
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-10     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.users

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.example.fabianzavala.testnux.MyApplication
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.adapters.UsersAdapter
import com.example.fabianzavala.testnux.databinding.ActivityUsersBinding
import com.example.fabianzavala.testnux.ui.login.LoginActivity
import com.example.fabianzavala.testnux.webservice.APIUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private val key = "MY_KEY"
    private lateinit var adapter: UsersAdapter
    private val usersData = mutableListOf<Users>()

    @Inject lateinit var userVM: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        //val app = MyApplication()
        //app.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_usersActivity)
        initRecyclerView()

        binding.loading.visibility = View.VISIBLE
        getAllUsers()

        binding.refreshUsers.setOnRefreshListener {
            getAllUsers()
        }
    }

    private fun initRecyclerView()
    {
        adapter = UsersAdapter(usersData)
        binding.usersRv.adapter = adapter
    }

    private fun getAllUsers()
    {

        APIUsers.usersService.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                binding.loading.visibility = View.GONE
                binding.refreshUsers.isRefreshing = false

                if (response.isSuccessful) {
                    val users: List<Users> = response.body()?: emptyList()
                    usersData.clear()
                    usersData.addAll(users)
                    adapter.notifyDataSetChanged()
                    userVM.insertUsers(users)
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                binding.loading.visibility = View.GONE
                binding.refreshUsers.isRefreshing = false

                //get users offline
                getOfflineUsers()

                Toast.makeText(
                            applicationContext, getString(R.string.networkError),
                            Toast.LENGTH_SHORT
                    ).show()
            }
        })

    }

    private fun getOfflineUsers() {
        userVM.users.observe(this, Observer {

            var usersAdapter: MutableList<Users> = mutableListOf()

            for (user in it) {
                val userAdapter = Users(
                        id = user.id,
                        name = user.name,
                        username = user.username,
                        email = user.email,
                        address = user.address,
                        phone = user.phone,
                        website = user.website,
                        company = user.company
                )

                usersAdapter.add(userAdapter)
            }

            usersData.clear()
            usersData.addAll(usersAdapter)
            adapter.notifyDataSetChanged()

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_signout -> {

                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.signoutMessage))
                        .setTitle(getString(R.string.warning))
                builder.apply {
                    setPositiveButton(getString(R.string.signout),
                            DialogInterface.OnClickListener { dialogInterface, i ->

                                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                                val editor = preferences.edit()
                                editor.remove(key)
                                editor.apply()

                                val intentSingout = Intent(context, LoginActivity::class.java)
                                startActivity(intentSingout)
                                finish()
                            })
                    setNegativeButton(getString(R.string.cancel),
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                dialogInterface.dismiss()
                            })
                }

                builder.create()
                builder.show()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}