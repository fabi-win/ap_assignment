package com.example.ap_assignment.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        progressBar.visibility = ProgressBar.INVISIBLE

        logIn.setOnClickListener(){
            startActivityForResult(intentFor<SiteListActivity>(), 1)
        }

        signUp.setOnClickListener(){

        }
    }
}