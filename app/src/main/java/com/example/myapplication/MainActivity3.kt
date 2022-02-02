package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myhope.R

lateinit var pref : SharedPreferences
class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("enter", MODE_PRIVATE)
        setContentView(R.layout.activity_main3)
        val tex = findViewById<EditText>(R.id.editText1)
        if (pref.contains("email")) {
            tex.setText(pref.getString("email",""))
        }
    }
    fun reg(view: View){
        val intent = Intent(this, Regis::class.java)
        startActivity(intent)
    }
    fun texted(view: View){
        val mail = findViewById<EditText>(R.id.editText1)
        val pass = findViewById<EditText>(R.id.editTextTextPassword)
        var enter = false
        if(mail.text.contains("@")){
            enter = true
        }
        if(pass.text.isEmpty()){enter = false}

        if (enter){
            val editor = pref.edit()
            editor?.putString("email",mail.text.toString())
            editor?.apply()
            editor?.putString("pass",pass.text.toString())
            editor?.apply()
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
    }

}
