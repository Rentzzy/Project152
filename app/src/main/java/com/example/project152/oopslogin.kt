package com.example.project152

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View

class oopslogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oopslogin)
    }

    fun gaslog(view: View?) {
        val it = Intent(this, register::class.java)
        startActivity(it)
    }

    fun loginbtn(view: View?) {
        val it = Intent(this, loginact::class.java)
        startActivity(it)
    }
}