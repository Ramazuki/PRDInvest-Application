package com.example.myjetpackfront

import android.app.Application
import com.example.myjetpackfront.data.AppContainer
import com.example.myjetpackfront.data.DefaultContainer

class MyApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}