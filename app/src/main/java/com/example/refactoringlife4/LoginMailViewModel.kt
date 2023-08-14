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
    val userfirebaseService = UserFirebaseService()

    fun checkAllFields(email: String, pass: String) {
        validFields.postValue(Utils.checkUser(email, pass))
    }

    fun loginUser(password: String, email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userfirebaseService.login(email, password)

            if (response.isSuccessful()) {
                _data.postValue(UserEvent.ShowSuccessView)
            } else {
                _data.postValue(
                    (UserEvent.ShowModalError(
                        "modal", "descripcion",
                        "cancel", "ok"
                    ))//cambiar este if x when con todos los eventos posibles
                )
            }
        }
    }
}
