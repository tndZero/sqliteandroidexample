package com.example.sqliteandroidexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    var dbHandler: DatabaseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        TextUserName.requestFocus()
        dbHandler = DatabaseHandler(this)
        ////Login
        buttonOK.setOnClickListener(){

                var success: Boolean

                success = dbHandler!!.checkUser(TextUserName.text.toString(),TextUserPass.text.toString())
                if (success){
                    val toast = Toast.makeText(this,"Login Successfully", Toast.LENGTH_LONG).show()
                    TextUserName.text.clear()
                    TextUserPass.text.clear()
                    val u = Intent(this,UserDetailActivity::class.java)
                    startActivity(u)
                }else{
                    val toast = Toast.makeText(this,"Login Not Successfully", Toast.LENGTH_LONG).show()
                }

        }

        //// Regis
        buttonRegis.setOnClickListener(){
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }


}
