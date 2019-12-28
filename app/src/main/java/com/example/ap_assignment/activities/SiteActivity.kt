package com.example.ap_assignment.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.models.SiteModel
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        info("Activity Started")

        btnAdd.setOnClickListener(){
            site.title = siteTitle.text.toString()
            site.descripton = siteDescription.text.toString()
            if(site.title.isNotEmpty()){
                info("addButtonPressed: $site")
            }
        }
    }

}
