package com.example.refactoringlife4

import androidx.lifecycle.ViewModelProvider
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.example.refactoringlife4.databinding.GenericLoadingBinding
import kotlinx.coroutines.launch


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var loadingDialog: Dialog? = null
    private lateinit var viewModel: RegisterViewModel
    val fireBaseResponse = UserFirebaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextObserver()
        initViewModel()
        observer()
        onClick()
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    private fun observer() {

        viewModel.data.observe(this) {
            when (it) {
                is UserEvent.ShowSuccessView -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                is UserEvent.ShowModalError -> {
                    Toast.makeText(this, it.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.validFields.observe(this){
            binding.btRegister.isEnabled = it
        }
    }

    fun onClick() {

        binding.ivRegisterBack.setOnClickListener {
            goToBack()
        }

        binding.btRegister.setOnClickListener {
          viewModel.registerUser(binding.etRegisterEmail.text.toString(), binding.etRegisterName.text.toString(), binding.etRegisterPassword.text.toString())
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
                it.toString().trim(),
                binding.etRegisterEmail.text.toString().trim(),
                binding.etRegisterName.text.toString().trim()
            )
        }
    }

}
