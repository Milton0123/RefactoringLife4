package com.example.refactoringlife4.ui.loginFireStore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.repository.UserRepository
import com.example.refactoringlife4.model.usesCases.LoginUseCase
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import com.example.refactoringlife4.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFireStoreViewModel(private val userUsesCase: UserUsesCase = UserUsesCase()) :
    ViewModel() {

    private val _data = MutableLiveData<LoginViewModelEvent>()
    val data: LiveData<LoginViewModelEvent> = _data
    val validFields = MutableLiveData<Boolean>()

    fun checkAllFields(email: String, pass: String) {
        validFields.postValue(Utils.checkUserLogin(email, pass))
    }

    fun loginUser(password: String, email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userUsesCase.login.invoke(email, password)

            when (response.status) {
                Result.Status.SUCCESS -> {
                    _data.postValue(LoginViewModelEvent.ShowSuccessView)
                }
                Result.Status.ERROR_PASSWORD -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            LoginViewModelEvent.ShowModalError(
                                it
                            )
                        }
                    )
                }
                Result.Status.EMAIL_DONT_EXIST -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            LoginViewModelEvent.ShowModalError(
                                it
                            )
                        }
                    )
                }
                Result.Status.ERROR_LOST_CONNECTION -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            LoginViewModelEvent.ShowModalError(
                                it

                            )
                        }
                    )
                }
                else -> {
                    _data.postValue(
                        (response.modalDialog?.let {
                            LoginViewModelEvent.ShowModalError(
                                it

                            )
                        })
                    )
                }
            }
        }
    }
}
