package com.example.ap_assignment.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ap_assignment.R
import com.example.ap_assignment.helpers.readImageFromPath
import com.example.ap_assignment.models.Site.SiteModel
import kotlinx.android.synthetic.main.card_list.view.*
import kotlinx.android.synthetic.main.card_list.view.siteTitle
import kotlinx.android.synthetic.main.card_list.view.visited

interface SiteListener {
    fun onSiteClick(site: SiteModel)
}

class SiteAdapter constructor(private var sites: List<SiteModel>,
                              private val listener: SiteListener
) :
    RecyclerView.Adapter<SiteAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_list, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val site = sites[holder.adapterPosition]
        holder.bind(site, listener)
    }

    override fun getItemCount(): Int = sites.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(site: SiteModel, listener: SiteListener) {
            itemView.siteTitle.text = site.title
            itemView.description.text = site.description
            itemView.visited.isChecked = site.visited
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, site.image1))
            itemView.setOnClickListener{listener.onSiteClick(site)}
        }
    }


}