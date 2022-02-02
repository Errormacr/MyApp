package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myhope.R

class photo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        val im = findViewById<ImageView>(R.id.imageView14)
        im.setImageDrawable(resources.getDrawable(R.drawable.photo2));

    }
}