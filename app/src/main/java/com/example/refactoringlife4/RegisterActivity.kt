package com.example.refactoringlife4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.util.PatternsCompat
import com.example.refactoringlife4.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToBack()
        setupTextWatchers()
        setupRegisterButton()
    }

    private fun goToBack() {
        binding.ivRegisterBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupTextWatchers() {
        binding.etRegisterName.addTextChangedListener(createTextWatcher(binding.etRegisterName))
        binding.etRegisterEmail.addTextChangedListener(createTextWatcher(binding.etRegisterEmail))
        binding.etRegisterPassword.addTextChangedListener(createTextWatcher(binding.etRegisterPassword))
    }

    private fun setupRegisterButton() {
        binding.btRegister.isEnabled = false

        binding.btRegister.setOnClickListener {
            if (areFieldsValid()) {
                // Realizar el registro aquí
            }
        }
    }

    private fun createTextWatcher(view: View): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar aquí
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btRegister.isEnabled = areFieldsValid()
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar aquí
            }
        }
    }

    private fun areFieldsValid(): Boolean {
        val name = binding.etRegisterName.text.toString().trim()
        val nameWords = name.split("\\s+".toRegex())
        val nameValid =
            name.isNotEmpty() && name.length >= 3 && name.length <= 30 && nameWords.size <= 2
                    && !name.contains("\\s{2,}".toRegex()) // Verificar que no haya más de un espacio en el medio

        val email = binding.etRegisterEmail.text.toString().trim()
        val emailValid = email.isNotEmpty() && email.length <= 30 && isValidEmail(email)

        val password = binding.etRegisterPassword.text.toString().trim()
        val passwordValid = password.isNotEmpty() && password.length >= 6 && password.length <= 30

        return nameValid && emailValid && passwordValid
    }

    private fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
}
