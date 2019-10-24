package com.example.sqliteandroidexample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $FIRST_NAME TEXT, $LAST_NAME TEXT, $USER_PASS TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // /Called when the database needs to be upgraded
    }

    //Inserting (Creating) data
    fun addUser(user: Users): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(FIRST_NAME, user.firstName)
        values.put(LAST_NAME, user.lastName)
        values.put(USER_PASS, user.passUser)



        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    //get all users
    fun getAllUsers(): String {
        var allUser: String = ""
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM users"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var id = cursor.getString(cursor.getColumnIndex(ID))
                    var firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME))
                    var lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME))
                    var passUser = cursor.getString(cursor.getColumnIndex(USER_PASS))

                    allUser = "$allUser\n$id  :  $firstName     $lastName    $passUser"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allUser
    }

    fun checkUser(userName:String,userPass:String):Boolean {
        var isUser : Boolean = false
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT id FROM users WHERE FirstName = '$userName' AND  PassUser = '$userPass'", null)
        if (cursor.getCount() > 0){
            cursor.moveToFirst()
            var id = cursor.getString(cursor.getColumnIndex("id"))

            isUser = true
        }
        cursor.close()
        db.close()
        return  isUser
    }

    companion object {
        private val DB_NAME = "UsersDB"
        private val DB_VERSIOM = 1
        private val TABLE_NAME = "users"
        private val ID = "id"
        private val FIRST_NAME = "FirstName"
        private val LAST_NAME = "LastName"
        private val USER_PASS = "PassUser"
    }
}