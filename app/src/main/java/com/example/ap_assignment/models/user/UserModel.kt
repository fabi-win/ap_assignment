package com.example.ap_assignment.models.user

import android.os.Parcelable
import com.example.ap_assignment.models.SiteModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(var id: Long = 0,
                     var email: String ="",
                     var password: String="",
                     var numberOfSites: Int = 0,
                     var numberVisited: Int = 0,
                     var sites: ArrayList<SiteModel> = arrayListOf()): Parcelable
{
    fun calcNumbOfSites() : Int
    {
        return sites.size
    }

    fun calcNumbVisited() : Int
    {
        var count = 0
        sites.forEach { if(it.visited) count++ }
        return count
    }
}