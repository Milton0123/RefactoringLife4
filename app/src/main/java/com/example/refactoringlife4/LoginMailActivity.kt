package com.example.refactoringlife4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityLoginMailBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginMailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginMailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mail)
        binding = ActivityLoginMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logIn()
    }

    private fun logIn() {

        binding.bnEnterLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                    } else {
                        try {
                            throw it.exception!!
                        } catch (invalidUserException: FirebaseAuthInvalidUserException) {
                            val errorCode = invalidUserException.errorCode
                            if (errorCode == "ERROR_USER_NOT_FOUND") {
                                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (invalidCredentialsException: FirebaseAuthInvalidCredentialsException) {
                            val errorCode =
                                invalidCredentialsException.errorCode // Puedes usar esto para manejar diferentes casos
                            if (errorCode == "ERROR_WRONG_PASSWORD") {
                                Toast.makeText(
                                    this,
                                    "Contraseña o usuario incorrecto",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } catch (invalidCredentialsException: FirebaseNetworkException) {
                            Toast.makeText(
                                this,
                                "No se pudo estrablecer la conexion",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

}