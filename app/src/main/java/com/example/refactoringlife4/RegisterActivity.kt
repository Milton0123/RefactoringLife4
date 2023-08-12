package com.example.refactoringlife4

import androidx.lifecycle.ViewModelProvider
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.core.widget.addTextChangedListener
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.example.refactoringlife4.databinding.GenericLoadingBinding
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var loadingDialog: Dialog? = null
    private lateinit var viewModel: RegisterViewModel
    val fireBaseResponse = FirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
        observer()


    }

    fun onClick() {

        binding.ivRegisterBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btRegister.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val userName = binding.etRegisterName.text.toString()
            val password = binding.etRegisterPassword.text.toString()

            lifecycleScope.launch {
                var responseStatus = fireBaseResponse.register(email, userName, password)
                viewModel.status(responseStatus)
            }

        }

    }


    fun observer() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.responseLiveData.observe(this) {
            //en esta parte en base a las respuestas que se obtengan se deben de mostrar las respuestas
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoadingDialog() {
        if (loadingDialog == null) {
            val dialogBinding = GenericLoadingBinding.inflate(layoutInflater)
            loadingDialog = Dialog(this)
            loadingDialog?.setContentView(dialogBinding.root)
            loadingDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            loadingDialog?.setCancelable(false)
        }
        loadingDialog?.show()
    }

    private fun hideLoadingDialog() {
        loadingDialog?.dismiss()
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
