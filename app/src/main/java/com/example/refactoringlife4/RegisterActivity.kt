package com.example.refactoringlife4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        register()
    }

    fun register() {

        binding.btRegister.setOnClickListener {
            val email: String = binding.etRegisterEmail.text.toString()
            val userName: String = binding.etRegisterName.text.toString()
            val password: String = binding.etRegisterPassword.text.toString()

            if (binding.etRegisterEmail.text.isNotEmpty() && binding.etRegisterName.text.isNotEmpty()
                && binding.etRegisterPassword.text.isNotEmpty()
            ) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        email, password
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show()
                            db.collection("Users").document(email)
                                .set(
                                    hashMapOf(
                                        "Email" to email,
                                        "Name" to userName,
                                        "pass" to password,
                                        "type" to "fullAccess"
                                    )
                                )

                        } else {
                            try {
                                throw it.exception!!
                            } catch (userCollisionException: FirebaseAuthUserCollisionException) {
                                val errorCode = userCollisionException.errorCode
                                if (errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {

                                    Toast.makeText(this, "email ya resgistrado", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } catch (networkException: FirebaseNetworkException) {

                                Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(this, "Error generico", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }
        }
    }
}
