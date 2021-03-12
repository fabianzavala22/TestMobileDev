/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to add functionality to ui
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-03-09     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.testnux.ui.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.fabianzavala.testnux.ui.users.UsersActivity
import com.example.fabianzavala.testnux.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private val key = "MY_KEY"
    private lateinit var preferences: SharedPreferences

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityLoginBinding.inflate(layoutInflater)
                val view = binding.root
                setContentView(view)

                val loginViewModel: LoginViewModel by viewModels()

                loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
                    val loginState = it ?: return@Observer

                    // disable ui button unless both username / password is valid
                    binding.login.isEnabled = loginState.isDataValid

                    if (loginState.usernameError != null) {
                        binding.usernameEdt.error = getString(loginState.usernameError)
                    }
                    if (loginState.passwordError != null) {
                        binding.password.error = getString(loginState.passwordError)
                    }
                })


                binding.usernameEdt.afterTextChanged {
                    loginViewModel.loginDataChanged(
                        binding.usernameEdt.text.toString(),
                        binding.password.text.toString()
                    )
                }

                binding.password.apply {
                    afterTextChanged {
                        loginViewModel.loginDataChanged(
                            binding.usernameEdt.text.toString(),
                            binding.password.text.toString()
                        )
                    }

                    binding.login.setOnClickListener {
                        binding.loading.visibility = View.VISIBLE

                        val editor = preferences.edit()
                        editor.putString(key,"signin")
                        editor.apply()
                        val intentLogin = Intent(context, UsersActivity::class.java)
                        startActivity(intentLogin)
                        finish()
                    }
                }
    }

    override fun onStart() {
        super.onStart()

        preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        val pref = preferences.getString(key,"signout")

        if(pref == "signin")
        {
            val intentLogin = Intent(this, UsersActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
    }

    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}