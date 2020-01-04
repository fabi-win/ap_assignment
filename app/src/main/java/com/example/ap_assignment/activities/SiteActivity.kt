package com.example.ap_assignment.activities


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ap_assignment.R
import com.example.ap_assignment.helpers.readImage
import com.example.ap_assignment.helpers.readImageFromPath
import com.example.ap_assignment.helpers.showImagePicker
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.SiteModel
import kotlinx.android.synthetic.main.activity_site.*
import kotlinx.android.synthetic.main.activity_site.siteTitle
import kotlinx.android.synthetic.main.activity_site_list.*
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()
    lateinit var app:MainApp
    var IMAGE_REQUEST_1 = 1
    var IMAGE_REQUEST_2 = 2
    var IMAGE_REQUEST_3 = 3
    var IMAGE_REQUEST_4 = 4

    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)


        info("site Activity started..")

        app = application as MainApp



        if (intent.hasExtra("site_edit")) {
            edit = true

            site = intent.extras?.getParcelable<SiteModel>("site_edit")!!
            siteTitle.setText(site.title)
            siteDescription.setText(site.description)

            siteVisited.setChecked(site.visited)

            siteImage.setImageBitmap(readImageFromPath(this, site.image1))
            if (site.image1 != null) {
                chooseImage.setText(R.string.change_site_image)
            }

            siteImage2.setImageBitmap(readImageFromPath(this, site.image2))
            if (site.image2 != null) {
                chooseImage2.setText(R.string.change_site_image2)
            }

            siteImage3.setImageBitmap(readImageFromPath(this, site.image2))
            if (site.image3 != null) {
                chooseImage3.setText(R.string.change_site_image3)
            }

            siteImage4.setImageBitmap(readImageFromPath(this, site.image2))
            if (site.image4 != null) {
                chooseImage4.setText(R.string.change_site_image4)
            }

            btnAdd.setText(R.string.save_site)
        }

        btnAdd.setOnClickListener() {
            site.title = siteTitle.text.toString()
            site.description = siteDescription.text.toString()
            site.visited = siteVisited.isChecked

            if (site.title.isEmpty()) {
                toast(R.string.enter_site_title)
            } else {
                if (edit) {
                    app.sites.update(site.copy())
                } else {
                    app.sites.create(site.copy())
                }
            }
            info("add Button Pressed: $siteTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }


        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST_1)
        }

        chooseImage2.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST_2)
        }

        chooseImage3.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST_3)
        }

        chooseImage4.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST_4)
        }

        //date
        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                //siteDate.setText()
            }

                siteDate.setOnClickListener {
                    DatePickerDialog(
                        this@SiteActivity, dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
                //end of date
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_site, menu)
        if (edit && menu != null) menu.getItem(1).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
            R.id.item_delete -> {
                app.sites.delete(site)
                finish()
            }
            R.id.item_logout ->{
                startActivityForResult<LoginActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST_1 -> {
                info("image request 1")
                if (data != null) {
                        site.image1 = data.getData().toString()
                        siteImage.setImageBitmap(readImage(this, resultCode, data))
                        chooseImage.setText(R.string.change_site_image)
                    }
            }
            IMAGE_REQUEST_2 -> {
                info("image request 2")
                if (data != null) {

                    site.image2 = data.getData().toString()
                    siteImage2.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage2.setText(R.string.change_site_image)

                }
            }
            IMAGE_REQUEST_3 -> {
                info("image request 3")
                if (data != null) {

                    site.image3 = data.getData().toString()
                    siteImage3.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage3.setText(R.string.change_site_image)
                }
            }
            IMAGE_REQUEST_4 -> {
                info("image request 4")
                if (data != null) {

                    site.image4 = data.getData().toString()
                    siteImage4.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage4.setText(R.string.change_site_image)
                }
            }
        }
    }




}
