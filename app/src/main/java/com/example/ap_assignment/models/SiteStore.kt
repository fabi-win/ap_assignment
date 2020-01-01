package com.example.ap_assignment.models

import com.example.ap_assignment.models.SiteModel

interface SiteStore {
    fun findAll(): List<SiteModel>
    fun create(site: SiteModel)
    fun update(site: SiteModel)
    fun delete(site: SiteModel)
}