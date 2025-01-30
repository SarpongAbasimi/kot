package com.todo

import android.app.Application
import com.todo.container.DefaultContainer

class ApplicationContainer : Application() {

    private val dbName = "thoughts"
    lateinit var container: DefaultContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(this, dbName)
    }
}