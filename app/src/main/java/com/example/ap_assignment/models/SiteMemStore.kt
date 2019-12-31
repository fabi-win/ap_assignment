package com.example.ap_assignment.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SiteMemStore: SiteStore, AnkoLogger {

    val sites = ArrayList<SiteModel>()

    override fun findAll(): List<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = getId()
        sites.add(site)
        logAll()
    }

    override fun update(site: SiteModel) {
        var foundSite: SiteModel? = sites.find { p -> p.id == site.id }
        if (foundSite != null) {
            foundSite.title = site.title
            foundSite.description = site.description
            foundSite.image1 = site.image1
            foundSite.image2 = site.image2
            foundSite.visited = site.visited
            logAll()
        }
    }

    override fun delete(site: SiteModel){
        sites.remove(site)
    }

    fun logAll(){
        sites.forEach{info("${it}")}
    }


}