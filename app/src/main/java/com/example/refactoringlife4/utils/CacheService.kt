package com.example.refactoringlife4.utils

import android.content.Context
import android.content.SharedPreferences

class CacheService(context: Context) {
    val userKey = "userSuccess"
    val shared: SharedPreferences = context.getSharedPreferences("name_user", Context.MODE_PRIVATE)

    fun setNameUser(user: String) {
        shared.edit().putString(userKey, user).apply()
    }

    fun getNameUser(): String? {
        return shared.getString(userKey, "")
    }

    fun clearUser() {
        shared.edit().clear().apply()
    }

}
