package com.example.refactoringlife4.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import com.example.refactoringlife4.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFireStoreViewModel(private val userUsesCase: UserUsesCase = UserUsesCase()) :
    ViewModel() {

    val validFields = MutableLiveData<Boolean>()
    private val _data = MutableLiveData<RegisterViewModelEvent>()
    val data: LiveData<RegisterViewModelEvent> = _data

    fun registerUser(email: String, username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userUsesCase.register.invoke(email, username, password)
            when (response.status) {
                Result.Status.SUCCESS -> {
                    _data.postValue(RegisterViewModelEvent.ShowSuccessView)
                }
                Result.Status.ERROR_EMAIL_EXIST -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            RegisterViewModelEvent.ShowModalError(
                                it
                            )
                        }
                    )
                }
                Result.Status.ERROR_LOST_CONNECTION -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            RegisterViewModelEvent.ShowModalError(
                                it
                            )
                        }
                    )
                }
                else -> {
                    _data.postValue(
                        response.modalDialog?.let {
                            RegisterViewModelEvent.ShowModalError(
                                it
                            )
                        }
                    )
                }

            }
        }
    }

    fun validateFields(email: String, password: String, name: String) {
        validFields.postValue(Utils.checkUserRegister(email, password, name))
    }

}
