package com.example.refactoringlife4.ui.loginFireStore.presenters

import android.content.Intent
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityLoginFireStoreBinding
import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModel
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelEvent
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelFactory
import com.example.refactoringlife4.ui.register.presenters.RegisterFireStoreActivity

class LoginFireStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFireStoreBinding
    private lateinit var viewModel: LoginFireStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFireStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateFields()
        getViewModel()
        observer()
        onClick()
    }

    private fun getViewModel() {
        viewModel = LoginFireStoreViewModelFactory().create(LoginFireStoreViewModel::class.java)
    }

    private fun observer() {
        viewModel.data.observe(this) {
            when (it) {
                is LoginFireStoreViewModelEvent.ShowSuccessView -> {
                    //enviar a siguiente activity
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }

                is LoginFireStoreViewModelEvent.ShowModalError -> {
                    Log.i("loginResult", it.modalDialog.toString())
                    showModal(it.modalDialog)
                    hideLayout()
                    modalButtons()
                }
            }
        }
        viewModel.validFields.observe(this) {
            binding.btEnterLogin.isEnabled = it
        }
    }

    private fun validateFields() {
        binding.etEmail.doAfterTextChanged { email ->
            viewModel.checkAllFields(
                email.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }
        binding.etPassword.doAfterTextChanged { pass ->
            viewModel.checkAllFields(
                binding.etEmail.text.toString().trim(),
                pass.toString().trim()
            )
        }
    }

    private fun goToRegister() {
        startActivity(Intent(this, RegisterFireStoreActivity::class.java))
    }

    private fun hideLayout() {
        binding.etEmail.visibility = View.GONE
        binding.etPassword.visibility = View.GONE
        binding.btEnterLogin.visibility = View.GONE
    }

    private fun showLayout() {
        binding.etEmail.visibility = View.VISIBLE
        binding.etPassword.visibility = View.VISIBLE
        binding.btEnterLogin.visibility = View.VISIBLE
    }

    private fun hideModal() {
        binding.modalError.root.visibility = View.GONE
    }

    private fun showModal(modalDialog: UserModel.ModalDialog) {
        binding.modalError.tvMessageModal.text = modalDialog.description
        binding.modalError.tvTitleModal.text = modalDialog.title
        binding.modalError.bt1Modal.text = modalDialog.firstAction
        binding.modalError.bt2Modal.text = modalDialog.secondAction
        binding.modalError.root.visibility = View.VISIBLE
    }

    private fun modalButtons() {
        when (binding.modalError.bt1Modal.text.toString()) {
            "" -> {
                binding.modalError.bt1Modal.visibility = View.GONE
            }

            "Registrar" -> {
                binding.modalError.bt1Modal.visibility = View.VISIBLE
                binding.modalError.bt1Modal.setOnClickListener {
                    goToRegister()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun onClick() {
        binding.ivBackLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btEnterLogin.setOnClickListener {
            hideKeyboard()
            viewModel.loginUser(binding.etPassword.text.toString(), binding.etEmail.text.toString())
        }

        binding.modalError.bt2Modal.setOnClickListener {
            showLayout()
            hideModal()
        }

    }
}
