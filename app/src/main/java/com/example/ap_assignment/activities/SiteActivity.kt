package com.example.ap_assignment.activities


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.helpers.readImage
import com.example.ap_assignment.helpers.readImageFromPath
import com.example.ap_assignment.helpers.showImagePicker
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.SiteModel
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()
    lateinit var app:MainApp

    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        app = application as MainApp

        toolbarAdd.title = "Test"
        setSupportActionBar(toolbarAdd)

        btnAdd.setOnClickListener() {
            site.title = siteTitle.text.toString()
            site.descripton = siteDescription.text.toString()
            if (site.title.isNotEmpty()) {
                app.sites.add(site.copy())
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else{
                toast(R.string.enter_site_title)
            }
        }

        chooseImage.setOnClickListener{
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_site, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    site.image = data.getData().toString()
                    siteImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
        if (intent.hasExtra("site_edit")) {
            //... as before
            siteImage.setImageBitmap(readImageFromPath(this, site.image))
        }
    }




}
