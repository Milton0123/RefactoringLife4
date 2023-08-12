package com.example.refactoringlife4.ui.Registrer.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.refactoringlife4.RegisterViewModel
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.example.refactoringlife4.ui.Login.presenter.LoginActivity


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        goToBack()
        setupTextObserver()
        setupRegisterButton()

        viewModel.isRegistrationValid.observe(this) { isValid ->
            binding.btRegister.isEnabled = isValid
        }
    }

    private fun goToBack() {
        binding.ivRegisterBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupTextObserver() {
        binding.etRegisterName.addTextChangedListener {
            viewModel.validateFields(
                it.toString().trim(),
                binding.etRegisterEmail.text.toString().trim(),
                binding.etRegisterPassword.text.toString().trim()
            )
        }

        binding.etRegisterEmail.addTextChangedListener {
            viewModel.validateFields(
                binding.etRegisterName.text.toString().trim(),
                it.toString().trim(),
                binding.etRegisterPassword.text.toString().trim()
            )
        }

        binding.etRegisterPassword.addTextChangedListener {
            viewModel.validateFields(
                binding.etRegisterName.text.toString().trim(),
                binding.etRegisterEmail.text.toString().trim(),
                it.toString().trim()
            )
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
