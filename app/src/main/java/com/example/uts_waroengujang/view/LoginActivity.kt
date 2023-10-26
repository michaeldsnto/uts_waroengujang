package com.example.uts_waroengujang.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.login()

        btnSignIn.setOnClickListener {
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()

            val listUser = viewModel.usersLD.value

            if (listUser != null) {
                var isUserFound = false
                for (user in listUser) {
                    if (user.username == username && user.password == password) {
                        isUserFound = true
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("waitressName", user.name)
                        intent.putExtra("waitressPhoto", user.photoUrl)
                        startActivity(intent)
                        finish()
                    }
                }
                if (!isUserFound) {
                    Toast.makeText(this, "Username atau password salah",  Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Eror",  Toast.LENGTH_SHORT).show()
            }
        }

    }
}