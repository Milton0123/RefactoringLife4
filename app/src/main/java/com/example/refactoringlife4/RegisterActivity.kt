package com.example.refactoringlife4

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityRegisterBinding
import com.example.refactoringlife4.databinding.GenericLoadingBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var db = FirebaseFirestore.getInstance()
    private var loadingDialog: Dialog? = null
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register()
    }

    fun register() {

        binding.btRegister.setOnClickListener {
            val email: String = binding.etRegisterEmail.text.toString()
            val userName: String = binding.etRegisterName.text.toString()
            val password: String = binding.etRegisterPassword.text.toString()

            if (binding.etRegisterEmail.text.isNotEmpty() && binding.etRegisterName.text.isNotEmpty()
                && binding.etRegisterPassword.text.isNotEmpty()
            ) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        email, password
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show()
                            db.collection("Users").document(email)
                                .set(
                                    hashMapOf(
                                        "Email" to email,
                                        "Name" to userName,
                                        "pass" to password,
                                        "type" to "fullAccess"
                                    )
                                )
                            showLoadingDialog()
                        } else {
                            try {
                                throw it.exception!!
                            } catch (userCollisionException: FirebaseAuthUserCollisionException) {
                                val errorCode = userCollisionException.errorCode
                                if (errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {

                                    Toast.makeText(this, "email ya resgistrado", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } catch (invalidCredentialsException: FirebaseAuthInvalidCredentialsException) {

                                val errorCode = invalidCredentialsException.errorCode
                                if (errorCode == "ERROR_INVALID_EMAIL") {
                                    Toast.makeText(this, "email invalido", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } catch (networkException: FirebaseNetworkException) {

                                Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(this, "Error generico", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
            }
        }

    }
//    fun showLoadingLayout() {
//        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        binding.inLoading.root.visibility = View.VISIBLE
//    }
//
//    fun hideLoadingLayout() {
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        binding.inLoading.root.visibility = View.GONE
//    }

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
