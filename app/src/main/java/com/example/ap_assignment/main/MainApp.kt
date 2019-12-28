package com.example.ap_assignment.main

import android.app.Application
import com.example.ap_assignment.models.SiteModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    val sites = ArrayList<SiteModel>()

    override fun onCreate() {
        super.onCreate()
        info("Placemark started")
    }
}