package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val validFields = MutableLiveData<Boolean>()
    private val _data = MutableLiveData<UserEvent>()
    val data: LiveData<UserEvent> = _data
    private val userFirebaseService = UserFirebaseService()

    fun registerUser(email: String,username: String, password: String  ){
        CoroutineScope(Dispatchers.IO).launch {
            val response = userFirebaseService.register(email, username, password)
        when (response.status){
            FireBaseResponse.Status.SUCCESS-> {
                _data.postValue(UserEvent.ShowSuccessView)
            }
            FireBaseResponse.Status.ERROR_EMAIL_EXIST-> {
                _data.postValue(UserEvent.ShowModalError(
                    "Error", "Email exist",
                    "Cancel", "Go to login"
                ))
            }
            FireBaseResponse.Status.ERROR_LOST_CONNECTION-> {
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
    fun validateFields(email: String,password: String,name: String) {
       validFields.postValue(Utils.checkUserRegister(email, password, name))
    }

}
