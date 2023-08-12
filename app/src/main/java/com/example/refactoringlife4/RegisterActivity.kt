package com.example.refactoringlife4

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.app.Dialog
import android.bluetooth.BluetoothAdapter.ERROR
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.example.refactoringlife4.databinding.GenericLoadingBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
