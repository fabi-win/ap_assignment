package com.example.ap_assignment.models.user

import com.example.ap_assignment.models.SiteModel

interface UserStore {


    fun findAll(): MutableList<UserModel>
    fun create(user: UserModel): Long
    fun update(user: UserModel)
    fun delete(user: UserModel)
    fun findNumbSites(user : UserModel) : Int
    fun findNumbVisited(user : UserModel) : Int

}
