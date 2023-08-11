package com.example.refactoringlife4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        register()
    }

    fun register() {
        val email: String = binding.etRegisterEmail.text.toString()
        val password: String = binding.etRegisterPassword.text.toString()

        binding.btRegister.setOnClickListener {
            if(binding.etRegisterEmail.text.isNotEmpty() && binding.etRegisterName.text.isNotEmpty()
                && binding.etRegisterPassword.text.isNotEmpty())

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}
