package com.example.refactoringlife4.ui.loginFireStore.presenters

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.databinding.ActivityLoginFireStoreBinding
import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.ui.onBoarding.presenters.OnBoardingActivity
import com.example.refactoringlife4.ui.login.presenters.LoginActivity
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModel
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelEvent
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelFactory
import com.example.refactoringlife4.ui.register.presenters.RegisterFireStoreActivity
import com.example.refactoringlife4.utils.Utils

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
                is LoginFireStoreViewModelEvent.ShowOnBoarding -> {
                    goToOnBoarding()
                }

                is LoginFireStoreViewModelEvent.ShowHome -> {
                    goToHome()
                }

                is LoginFireStoreViewModelEvent.ShowModalError -> {
                    showViewError(it.modalDialog)
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

    private fun goToOnBoarding() {
        val extras = Bundle()
        extras.putString("email", binding.etEmail.text.toString())
        Utils.startActivityWithSlideToLeft(this, OnBoardingActivity::class.java, extras)
    }

    private fun goToHome() {
        Utils.startActivityWithSlideToLeft(this, HomeActivity::class.java, null)
    }

    private fun goToRegister() {
        Utils.startActivityWithSlideToLeft(this, RegisterFireStoreActivity::class.java, null)
    }

    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    override fun onBackPressed() {
        goToBack()
    }

    private fun hideViewModal() {
        binding.modalError.root.visibility = View.GONE
    }

    private fun showViewError(modalDialog: UserModel.ModalDialog) {
        hideViewLoading()
        createModal(modalDialog)
    }

    private fun createModal(modalDialog: UserModel.ModalDialog) {
        binding.modalError.tvMessageModal.text = modalDialog.description
        binding.modalError.tvTitleModal.text = modalDialog.title
        binding.modalError.bt1Modal.text = modalDialog.firstAction
        binding.modalError.bt2Modal.text = modalDialog.secondAction
        binding.modalError.root.visibility = View.VISIBLE

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
        binding.modalError.root.visibility = View.VISIBLE
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun onClick() {
        binding.ivBackLogin.setOnClickListener {
            goToBack()
        }

        binding.btEnterLogin.setOnClickListener {
            showViewLoading()
            hideKeyboard()
            viewModel.loginUser(binding.etPassword.text.toString(), binding.etEmail.text.toString())
        }

        binding.modalError.bt2Modal.setOnClickListener {
            hideViewModal()
        }

    }

    private fun showViewLoading() {
        binding.pbLoading.root.visibility = View.VISIBLE
    }

    private fun hideViewLoading() {
        binding.pbLoading.root.visibility = View.GONE
    }
}
