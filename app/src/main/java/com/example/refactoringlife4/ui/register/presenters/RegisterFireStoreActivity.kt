package com.example.refactoringlife4.ui.register.presenters

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.ui.register.viewmodel.RegisterFireStoreViewModel
import com.example.refactoringlife4.databinding.ActivityRegisterFireStoreBinding
import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.ui.congratulations.CongratulationsActivity
import com.example.refactoringlife4.ui.loginFireStore.presenters.LoginFireStoreActivity
import com.example.refactoringlife4.ui.register.viewmodel.RegisterFireStoreViewModelFactory
import com.example.refactoringlife4.ui.register.viewmodel.RegisterViewModelEvent
import com.example.refactoringlife4.utils.Utils


class RegisterFireStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterFireStoreBinding
    private lateinit var viewModel: RegisterFireStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFireStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextObserver()
        getViewModel()
        observer()
        onClick()
    }

    private fun getViewModel() {
        viewModel =
            RegisterFireStoreViewModelFactory().create(RegisterFireStoreViewModel::class.java)
    }

    private fun observer() {

        viewModel.data.observe(this) {
            when (it) {
                is RegisterViewModelEvent.ShowSuccessView -> {
                    //enviar a siguiente activity
                    GoToCongratulation()
                }

                is RegisterViewModelEvent.ShowModalError -> {
                    showModal(it.modalDialog)
                    hideLayout()
                    modalButtons()
                }
            }
        }
        viewModel.validFields.observe(this) {
            binding.btRegister.isEnabled = it
        }
    }

    private fun GoToCongratulation() {
        Utils.startActivityWithSlideToLeft(this, CongratulationsActivity::class.java, null)
    }

    private fun goToLogin() {
        Utils.startActivityWithSlideToLeft(this, LoginFireStoreActivity::class.java, null)
    }

    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    override fun onBackPressed() {
        goToBack()
    }

    private fun hideLayout() {
        binding.etRegisterName.visibility = View.GONE
        binding.etRegisterEmail.visibility = View.GONE
        binding.etRegisterPassword.visibility = View.GONE
        binding.btRegister.visibility = View.GONE
    }

    private fun showLayout() {
        binding.etRegisterName.visibility = View.VISIBLE
        binding.etRegisterEmail.visibility = View.VISIBLE
        binding.etRegisterPassword.visibility = View.VISIBLE
        binding.btRegister.visibility = View.VISIBLE
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

            "Login" -> {
                binding.modalError.bt1Modal.visibility = View.VISIBLE
                binding.modalError.bt1Modal.setOnClickListener {
                    goToLogin()
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

        binding.ivRegisterBack.setOnClickListener {
            goToBack()
        }

        binding.btRegister.setOnClickListener {
            hideKeyboard()
            viewModel.registerUser(
                binding.etRegisterEmail.text.toString(),
                binding.etRegisterName.text.toString(),
                binding.etRegisterPassword.text.toString()
            )
        }

        binding.modalError.bt2Modal.setOnClickListener {
            showLayout()
            hideModal()
        }

    }

    private fun setupTextObserver() {
        binding.etRegisterName.doAfterTextChanged {
            viewModel.validateFields(
                binding.etRegisterEmail.text.toString().trim(),
                binding.etRegisterPassword.text.toString().trim(),
                it.toString().trim()
            )
        }

        binding.etRegisterEmail.doAfterTextChanged {
            viewModel.validateFields(
                it.toString().trim(),
                binding.etRegisterPassword.text.toString().trim(),
                binding.etRegisterName.text.toString().trim()
            )
        }

        binding.etRegisterPassword.doAfterTextChanged {
            viewModel.validateFields(
                binding.etRegisterEmail.text.toString().trim(),
                it.toString().trim(),
                binding.etRegisterName.text.toString().trim()
            )
        }
    }


}
