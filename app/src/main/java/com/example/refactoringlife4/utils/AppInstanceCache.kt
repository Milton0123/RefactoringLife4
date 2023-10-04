package com.example.refactoringlife4.utils

import android.app.Application
import com.example.refactoringlife4.model.service.CacheService

class AppInstanceCache : Application() {
    companion object {
        lateinit var getInstance: CacheService
    }

    override fun onCreate() {
        super.onCreate()
        getInstance = CacheService(applicationContext)
    }

}
