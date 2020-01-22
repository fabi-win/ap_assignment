package com.example.ap_assignment.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
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
                                app.users.create(user.copy())
                                toast("User created")
                                startActivityForResult<LoginActivity>(0)
                            }
                        }
                    }

            }
        }
    }
