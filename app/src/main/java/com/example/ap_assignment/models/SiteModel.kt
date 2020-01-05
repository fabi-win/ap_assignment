package com.example.ap_assignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SiteModel(
    var id: Long = 0,
    var title: String = "",
    var visited: Boolean = false,
    var description: String = "",
    var image1: String = "",
    var image2: String = "",
    var image3: String = "",
    var image4: String = "",
    var date: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 0f
): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f): Parcelable

