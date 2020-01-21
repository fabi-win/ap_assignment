package com.example.ap_assignment.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.ap_assignment.R
import com.example.ap_assignment.main.MainApp
import com.example.ap_assignment.models.user.UserModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.actvitiy_setting.*
import org.jetbrains.anko.intentFor


class SettingActivity: AppCompatActivity() {

    var user = UserModel()

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        progressBar.visibility = ProgressBar.INVISIBLE

        app = application as MainApp

        user = intent.getParcelableExtra("user")

        email.setText(user.email)
        password.setText(user.password)
        total.text = " ${user.sites.size}"
        visited.text = " ${user.numberVisited}"

        save.setOnClickListener(){
            user.email = email.text.toString()
            user.password = password.text.toString()

            app.users.update(user.copy())

            startActivityForResult(intentFor<SiteListActivity>().putExtra("user", user),1)
            setResult(AppCompatActivity.RESULT_OK)
        }

        cancel.setOnClickListener(){
            startActivity(intentFor<SiteListActivity>().putExtra("user", user))
        }
    }
}