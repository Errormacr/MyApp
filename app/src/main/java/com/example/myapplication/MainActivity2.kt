package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myhope.R

class MainActivity2 : AppCompatActivity()

{
    fun goSecond(view: View){
        val intent = Intent(this, MainActivity3::class.java)

        startActivity(intent)

    }
    fun reg(view: View){
        val intent = Intent(this, Regis::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("enter", MODE_PRIVATE)
        setContentView(R.layout.activity_main2)
        val intents = Intent(this, MainActivity4::class.java)
        if (pref.contains("pass")) {
            startActivity(intents)
        }

    }



}