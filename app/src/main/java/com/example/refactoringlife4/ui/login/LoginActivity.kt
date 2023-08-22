package com.example.refactoringlife4.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityLoginBinding
import com.example.refactoringlife4.ui.loginFireStore.presenters.LoginFireStoreActivity
import com.example.refactoringlife4.ui.onBoarding.presenters.OnBoardingActivity
import com.example.refactoringlife4.ui.register.presenters.RegisterFireStoreActivity
import com.example.refactoringlife4.utils.Utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private val GOOGLE_SING_IN = 100
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

        screenSplash.setKeepOnScreenCondition { false }
    }

    private fun setup() {
        binding.btLoginFireStore.setOnClickListener {
            goLoginEmail()
        }

        binding.btRegister.setOnClickListener {
            goRegisterEmail()
        }

        binding.btLoginGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }
    }

    private fun goRegisterEmail() {
        Utils.startActivityWithSlideToLeft(this, RegisterFireStoreActivity::class.java, null)
        finish()
    }

    private fun goLoginEmail() {
        Utils.startActivityWithSlideToLeft(this, LoginFireStoreActivity::class.java, null)
        finish()
    }

    private fun goToOnBoarding() {
        Utils.startActivityWithSlideToLeft(this, OnBoardingActivity::class.java, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SING_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    account.getIdToken()?.let { idToken ->
                        val credential = GoogleAuthProvider.getCredential(idToken, null)
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    goToOnBoarding()
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Error en la autenticación",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } ?: run {
                        Toast.makeText(
                            this,
                            "El token de autenticación es nulo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "No se obtuvo un correo", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
