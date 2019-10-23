package com.example.sqliteandroidexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        TextUserName.requestFocus()

        buttonOK.setOnClickListener(){
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}
