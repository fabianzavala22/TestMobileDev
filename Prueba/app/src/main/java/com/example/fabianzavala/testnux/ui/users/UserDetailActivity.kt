package com.example.fabianzavala.testnux.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fabianzavala.testnux.ui.posts.PostsActivity
import com.example.fabianzavala.testnux.R
import com.example.fabianzavala.testnux.ui.todos.TodosActivity
import com.example.fabianzavala.testnux.Users
import com.example.fabianzavala.testnux.databinding.ActivityDetailUserBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_DetailActivity)

        val itemDetail: Users = intent.extras?.getSerializable("itemDetail") as Users

        with(binding)
        {
            nameTv.text       = itemDetail.name
            userNameTv.text   = itemDetail.username
            emailTv.text      = itemDetail.email
            userPhoneTv.text  = itemDetail.phone
            websiteTv.text    = itemDetail.website

            fullAddressTv.text = "${itemDetail.address.street},${itemDetail.address.suite}," +
                    "${itemDetail.address.city},${itemDetail.address.zipcode}"

            companyNameTv.text  = itemDetail.company.name + "," + itemDetail.company.catchPhrase +
                    "," + itemDetail.company.bs

        }

        binding.postBtn.setOnClickListener {

            val intentPost = Intent(this, PostsActivity::class.java)
            intentPost.putExtra("userId",itemDetail.id)
            startActivity(intentPost)
        }

        binding.todosBtn.setOnClickListener {

            val intentTodos = Intent(this, TodosActivity::class.java)
            intentTodos.putExtra("userId",itemDetail.id)
            startActivity(intentTodos)
        }
    }
}