package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhope.R
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {
    private fun goMain(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timer().schedule(1000){goMain()}
    }

    }