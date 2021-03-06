package com.example.ap_assignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.SiteModel
import com.example.ap_assignment.models.user.UserModel


import kotlinx.android.synthetic.main.activity_site_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import java.lang.Exception

class SiteListActivity: AppCompatActivity(), SiteListener {

    var user = UserModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        try
        {
            user = intent.getParcelableExtra("user") as UserModel
        }
        catch (e: Exception)
        {
        }

        user.numberOfSites = getSites()
        user.numberVisited = getVisited()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadSites()
    }

    private fun getVisited(): Int {
        var i = 0
        var sites = arrayOf<SiteModel>()
        sites = app.sites.findAll().toTypedArray()
        for(site in sites){
            if(site.userID == user.id){
                if(site.visited) i++
            }
        }
        return i
    }

    private fun getSites(): Int {
        var i = 0
        var sites = arrayOf<SiteModel>()
        sites = app.sites.findAll().toTypedArray()
        for(site in sites){
            if(site.userID == user.id) i++
        }
        return i
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        user.numberOfSites = getSites()
        user.numberVisited = getVisited()
        when (item?.itemId) {
            R.id.item_add -> {
                startActivityForResult(intentFor<SiteActivity>().putExtra("user", user), 1)
            }
            R.id.item_logout -> {
                startActivityForResult<LoginActivity>(0)
            }
            R.id.item_settings -> {
                startActivityForResult(intentFor<SettingActivity>().putExtra("user", user), 1)
            }
        }
            return super.onOptionsItemSelected(item)
        }


    override fun onSiteClick(site: SiteModel) {
        startActivityForResult(intentFor<SiteActivity>().putExtra("site_edit", site), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadSites()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadSites() {
        showSites(app.sites.findAll())
    }

    fun showSites (sites: List<SiteModel>) {
        var showedSites = arrayOf<SiteModel>()
        for(site in sites) {
            if (site.userID == this.user.id){
                showedSites += site
            }
        }
        recyclerView.adapter = SiteAdapter(showedSites.toList(), this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


}


