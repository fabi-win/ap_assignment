package com.example.ap_assignment.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    override fun onCreate() {
        super.onCreate()
        info("Placemark started")
    }
}