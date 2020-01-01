package com.example.ap_assignment.models.user

interface UserStore {


    fun findAll(): MutableList<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
}
