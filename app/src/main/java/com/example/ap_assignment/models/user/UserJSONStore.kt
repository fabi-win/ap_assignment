package com.example.ap_assignment.models.user

import android.content.Context
import com.example.ap_assignment.helpers.exists
import com.example.ap_assignment.helpers.read
import com.example.ap_assignment.helpers.write
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.util.*
import kotlin.collections.ArrayList


val JSON_FILE = "users.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<UserModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}


class UserJSONStore : UserStore, AnkoLogger {

    val context: Context
    var users = mutableListOf<UserModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        return users
    }

    override fun create(user: UserModel): Long {
        user.id = generateRandomId()
        users.add(user)
        serialize()
        return user.id
    }


    override fun update(user: UserModel) {
        var foundUser: UserModel? = users.find { p -> p.id == user.id }
        if (foundUser != null) {
            foundUser.id = user.id
            foundUser.email = user.email
            foundUser.password = user.password
            foundUser.sites = user.sites
            foundUser.numberOfSites = user.calcNumbOfSites()
            foundUser.numberVisited = user.calcNumbVisited()
        }
        serialize()
    }

    override fun findNumbVisited(user: UserModel): Int
    {
        user.sites.forEach{ if (it.visited) user.numberOfSites++ }
        return user.numberVisited
    }

    override fun findNumbSites(user: UserModel): Int
    {
        return user.sites.size
    }

    override fun delete(user: UserModel){
        users.remove(user)
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE
        )
        users = Gson().fromJson(jsonString, listType)
    }
}