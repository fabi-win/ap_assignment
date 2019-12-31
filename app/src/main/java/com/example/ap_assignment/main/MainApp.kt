package com.example.ap_assignment.main

import android.app.Application
import com.example.ap_assignment.models.Site.SiteJSONStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    lateinit var sites: SiteJSONStore

    override fun onCreate() {
        super.onCreate()
        sites =
            SiteJSONStore(
                applicationContext
            )
        info("Placemark started")

    }
}