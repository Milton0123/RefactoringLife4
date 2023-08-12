package com.example.refactoringlife4.ui.LoginMail.presenter

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/example/refactoringlife4/ui/LoginMail/presenter/LoginMailActivity.kt
import com.example.refactoringlife4.R
=======
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.refactoringlife4.databinding.ActivityLoginMailBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch
>>>>>>> 138b3a54ca023dacc4ed3871abbbddfde37988dc:app/src/main/java/com/example/refactoringlife4/LoginMailActivity.kt

class LoginMailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginMailBinding
    private lateinit var viewModel: LoginViewModel
    val fireBaseResponse = FirebaseService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mail)
        binding = ActivityLoginMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
        observer()
    }
<<<<<<< HEAD:app/src/main/java/com/example/refactoringlife4/ui/LoginMail/presenter/LoginMailActivity.kt
=======

    private fun onClick() {
        binding.ivBackLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.bnEnterLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                var responseStatus = fireBaseResponse.login(email, password)
                viewModel.status(responseStatus)
            }
        }
    }

    fun observer() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.responseLiveData.observe(this) {
            //en esta parte en base a las respuestas que se obtengan se deben de mostrar las respuestas
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

>>>>>>> 138b3a54ca023dacc4ed3871abbbddfde37988dc:app/src/main/java/com/example/refactoringlife4/LoginMailActivity.kt
}
