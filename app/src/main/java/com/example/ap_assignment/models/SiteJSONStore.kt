package com.example.ap_assignment.models

import android.content.Context
import com.example.ap_assignment.helpers.exists
import com.example.ap_assignment.helpers.read
import com.example.ap_assignment.helpers.write
import com.example.ap_assignment.models.user.UserModel
import com.example.ap_assignment.models.user.UserStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import org.jetbrains.anko.AnkoLogger
import java.util.*

val JSON_FILE = "sites.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<SiteModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SiteJSONStore : SiteStore, AnkoLogger {

    val context: Context
    var sites = mutableListOf<SiteModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }


    override fun update(site: SiteModel) {
        var foundSite: SiteModel? = sites.find { p -> p.id == site.id }
        if (foundSite != null) {
            foundSite.title = site.title
            foundSite.description = site.description
            foundSite.image1 = site.image1
            foundSite.image2 = site.image2
            foundSite.image3 = site.image3
            foundSite.image4 = site.image4
            foundSite.visited = site.visited
        }
    }

    override fun delete(site: SiteModel) {
        sites.remove(site)
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(sites,
            listType
        )
        write(context,
            JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context,
            JSON_FILE
        )
        sites = Gson().fromJson(jsonString,
            listType
        )
    }
}

