package com.example.ap_assignment.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.SiteModel
import com.example.ap_assignment.models.user.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressBar
import kotlinx.android.synthetic.main.activity_login.signUp
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class SignupActivity: AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        progressBar.visibility = ProgressBar.INVISIBLE

        app = application as MainApp

        signUp.setOnClickListener() {

            var users = app.users.findAll()
            var userAsigned = false

                    if (passwordsu.text.toString() != passwordsu2.text.toString()) {
                        toast("Your typed password doesn't match!")
                    } else {
                        if (passwordsu.text.toString() == "") {
                            toast("Your password can't be blank")
                        } else {
                            for(user in users){
                                if(user.email == emailsu.text.toString()) {
                                    toast("Email already asigned")
                                    userAsigned = true
                                }
                            }
                            if(userAsigned == false) {
                                var user = UserModel()
                                user.email = emailsu.text.toString()
                                user.password = passwordsu.text.toString()
                                var id = app.users.create(user.copy())
                                user.id = id
                                toast("User created")
                                createInitialSites(user)
                                startActivityForResult<LoginActivity>(0)
                            }
                        }
                    }

            }
        }

    private fun createInitialSites(user: UserModel){
        var site1 = SiteModel()
        site1.userID = user.id
        site1.title = "Dom"
        site1.description= "Biggest church in Regensburg"
        site1.image1 = "content://com.android.providers.downloads.documents/document/111"
        site1.lat = 49.019620
        site1.lng = 12.098560
        site1.zoom = 15f
        app.sites.create(site1)

        var site2 = SiteModel()
        site2.userID = user.id
        site2.title = "Irish Harp"
        site2.description = "Bar in Regensburg"
        site2.image1="content://com.android.providers.downloads.documents/document/113"
        site2.lat = 49.021009
        site2.lng = 12.097599
        site2.zoom = 15f
        app.sites.create(site2)

        var site3 = SiteModel()
        site3.userID = user.id
        site3.title = "Wurstkuchl"
        site3.description = "Oldest Restaurant in Germany"
        site3.image1="content://com.android.providers.downloads.documents/document/112"
        site3.lat = 49.021122
        site3.lng = 12.097885
        site3.zoom = 15f
        app.sites.create(site3)
    }
}
