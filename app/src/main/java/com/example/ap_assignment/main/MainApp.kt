package com.example.ap_assignment.main

import android.app.Application

import com.example.ap_assignment.models.SiteJSONStore
import com.example.ap_assignment.models.user.UserJSONStore
import org.jetbrains.anko.AnkoLogger

class MainApp : Application(), AnkoLogger {

    lateinit var users: UserJSONStore
    lateinit var sites: SiteJSONStore


    override fun onCreate() {
        super.onCreate()

        users = UserJSONStore(applicationContext)

        sites = SiteJSONStore(applicationContext)




    }
}