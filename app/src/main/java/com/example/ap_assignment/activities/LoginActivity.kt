package com.example.ap_assignment.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.user.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class LoginActivity: AppCompatActivity() {

    var user = UserModel()

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        progressBar.visibility = ProgressBar.INVISIBLE

        logIn.setOnClickListener(){

            var users = app.users.findAll()

            for(user in users){
                if(user.email == email.text.toString()){
                    if(user.password == password.text.toString()){
                        startActivityForResult(intentFor<SiteListActivity>(), 1)
                    }
                }
                toast(R.string.no_email_found)
            }
        }

        signUp.setOnClickListener(){
            user.email = email.text.toString()
            user.password = password.text.toString()

            //if (user.email != null){
               // if (user.password != null){
                    //app.users.create(user.copy())
                   // toast(R.string.user_created)
                //}
          //  }
           // else{
                toast(R.string.enter_email)
                startActivityForResult(intentFor<SiteListActivity>(), 1)

            //}
        }
    }
}