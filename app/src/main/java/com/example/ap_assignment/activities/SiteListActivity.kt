package com.example.ap_assignment.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.SiteModel


import kotlinx.android.synthetic.main.activity_site_list.*
import kotlinx.android.synthetic.main.card_list.view.*
import org.jetbrains.anko.startActivityForResult

class SiteListActivity: AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = SiteAdapter(app.sites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<SiteActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }


    class SiteAdapter constructor(private var sites: List<SiteModel>) :
        RecyclerView.Adapter<SiteAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            return MainHolder(
                LayoutInflater.from(parent?.context).inflate(
                    R.layout.card_list,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val site = sites[holder.adapterPosition]
            holder.bind(site)
        }

        override fun getItemCount(): Int = sites.size

        class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(site: SiteModel) {
                itemView.siteTitle.text = site.title
                itemView.description.text = site.descripton
            }
        }
    }
}