package com.example.refactoringlife4.model.service

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.io.Serializable

class CacheService(context: Context) : Serializable {
    val userKey = "userSuccess"
    val shared: SharedPreferences = context.getSharedPreferences("name_user", Context.MODE_PRIVATE)

    fun setNameUser(user: String) {
        Log.i("userStateCache", user)
        shared.edit().putString(userKey, user).apply()
    }

    fun getNameUser(): String? {
        return shared.getString(userKey, "")
    }

    fun clearUser() {
        shared.edit().clear().apply()
    }

}
