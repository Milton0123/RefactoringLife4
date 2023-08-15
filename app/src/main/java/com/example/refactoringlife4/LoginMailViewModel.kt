package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginMailViewModel : ViewModel() {
    private val _data = MutableLiveData<UserEvent>()
    val data: LiveData<UserEvent> = _data
    val validFields = MutableLiveData<Boolean>()
    private val userFirebaseService = UserFirebaseService()

    fun checkAllFields(email: String, pass: String) {
        validFields.postValue(Utils.checkUserLogin(email, pass))
    }

    fun loginUser(password: String, email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userFirebaseService.login(email, password)

            when(response.status){
                FireBaseResponse.Status.SUCCESS -> {
                    _data.postValue(UserEvent.ShowSuccessView)
                }
                FireBaseResponse.Status.ERROR_PASSWORD -> {
                    _data.postValue(UserEvent.ShowModalError(
                        "Error", "Password or email wrong",
                        "Cancel", "Ok"
                    ))
                }
                FireBaseResponse.Status.EMAIL_DONT_EXIST -> {
                    _data.postValue(UserEvent.ShowModalError(
                        "Error", "Account dosent exist",
                        "Cancel", "Go to register"
                    ))
                }
                FireBaseResponse.Status.ERROR_LOST_CONNECTION -> {
                    _data.postValue(UserEvent.ShowModalError(
                        "Error", "Lost connection",
                        "Cancel", "Ok"
                    ))
                }else->{
                _data.postValue(
                    (UserEvent.ShowModalError(
                        "Error", "Generic Error",
                        "Cancel", "Ok"
                    ))
                )
                }
            }
        }
    }
}
