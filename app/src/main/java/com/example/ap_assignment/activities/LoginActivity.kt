package com.example.ap_assignment.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.user.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class LoginActivity: AppCompatActivity() {

    var user = UserModel()

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        progressBar.visibility = ProgressBar.INVISIBLE

        app = application as MainApp

        logIn.setOnClickListener(){

            var users = app.users.findAll()
            var noMail = 1

            for(user in users){
                noMail = 1
                if(user.email == email.text.toString()){
                    if(user.password == password.text.toString()){
                        noMail = 3 //no toast is shown
                        startActivityForResult(intentFor<SiteListActivity>().putExtra("user", user), 1)
                    }
                    if (noMail != 3) noMail = 2
                }
            }
            //if(noMail == 1) toast(R.string.no_email_found)
            //if(noMail == 2)toast(R.string.wrong_password)

        }

        signUp.setOnClickListener() {
            startActivityForResult<SignupActivity>(0)
        }
    }
}