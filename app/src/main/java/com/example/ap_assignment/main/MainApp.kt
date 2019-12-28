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
        sites.add(SiteModel("one", "dief a bcvxvcbyx f"))
        sites.add(SiteModel("two", "dfe a hjliuli f"))
        sites.add(SiteModel("dree", "khlilil a dafe f"))
        sites.add(SiteModel("one", "dief a bcvxvcbyx f"))
        sites.add(SiteModel("two", "dfe a hjliuli f"))
        sites.add(SiteModel("dree", "khlilil a dafe f"))
        sites.add(SiteModel("one", "dief a bcvxvcbyx f"))
        sites.add(SiteModel("two", "dfe a hjliuli f"))
        sites.add(SiteModel("dree", "khlilil a dafe f"))
    }
}