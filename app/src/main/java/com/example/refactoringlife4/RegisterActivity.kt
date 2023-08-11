package com.example.refactoringlife4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.util.PatternsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.refactoringlife4.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        goToBack()
        setupTextWatchers()
        setupRegisterButton()

        viewModel.isRegistrationValid.observe(this, Observer { isValid ->
            binding.btRegister.isEnabled = isValid
        })
    }

    private fun goToBack() {
        binding.ivRegisterBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupTextWatchers() {
        val fields =
            listOf(binding.etRegisterName, binding.etRegisterEmail, binding.etRegisterPassword)

        fields.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // No se necesita implementar aquí
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // No se necesita implementar aquí
                }

                override fun afterTextChanged(s: Editable?) {
                    val name = binding.etRegisterName.text.toString().trim()
                    val email = binding.etRegisterEmail.text.toString().trim()
                    val password = binding.etRegisterPassword.text.toString().trim()

                    viewModel.validateFields(name, email, password)
                }
            })
        }
    }

    private fun setupRegisterButton() {
        binding.btRegister.isEnabled = false

        binding.btRegister.setOnClickListener {
            val name = binding.etRegisterName.text.toString().trim()
            val email = binding.etRegisterEmail.text.toString().trim()
            val password = binding.etRegisterPassword.text.toString().trim()

            viewModel.validateFields(name, email, password)
        }
    }
}
