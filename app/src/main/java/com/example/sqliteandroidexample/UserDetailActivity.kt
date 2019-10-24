package com.example.sqliteandroidexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    var dbHandler: DatabaseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        dbHandler = DatabaseHandler(this)

        textViewFname.text = ""
        var i = intent
        val nameUser:String = i.getStringExtra("userName")
        var user = dbHandler!!.userDetail(nameUser)
        textViewFname.text  = user
    }


}
