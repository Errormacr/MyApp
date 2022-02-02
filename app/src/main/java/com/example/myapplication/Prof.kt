package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.myhope.R
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import androidx.gridlayout.widget.GridLayout;
import androidx.appcompat.widget.AppCompatImageButton


class Prof : AppCompatActivity() {
    private val REQUEST_CODE = 1
    lateinit var pref: SharedPreferences

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            val picView = layoutInflater.inflate(R.layout.photo,null)
            val pic = picView.findViewById<ImageView>(R.id.d3)
            val layout = findViewById<GridLayout>(R.id.container)
            /*val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )*/
            pic.setImageURI(data?.data)
            val imageURI = data?.data
            val hours = Calendar.getInstance().time.hours.toString()
            val mins = Calendar.getInstance().time.minutes.toString()
            val time =  when(mins.toInt()){
                in 0..9-> "$hours:0$mins"
                else -> "$hours:$mins"
            }
            picView.findViewById<TextView>(R.id.d1).text = time
            layout.addView(picView)
            val picString = pref.getString("pictures","")

            var picArr = JSONArray()
            if (picString != ""){
                picArr = JSONArray(picString)
            }
            this.grantUriPermission(this.packageName, imageURI, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION;
            if (imageURI != null) {
                this.contentResolver.takePersistableUriPermission(imageURI, takeFlags)
            };
            val imageUriStr = imageURI.toString()
            val jsonValues = ArrayList<JSONObject>()
            for (i in 0 until picArr.length())
                jsonValues.add(picArr.getJSONObject(i))
            val newPicJson = "{\"time\":\"$time\",\"uri\":\"$imageUriStr\"}"
            jsonValues.add(JSONObject(newPicJson))
            picArr = JSONArray(jsonValues)
            setText("pictures",picArr.toString())

            //Add new button and delete previous
            var picB = layout.findViewById<ImageButton>(R.id.imageButton2)
            layout.removeView(picB)
            val newPicB = layoutInflater.inflate(R.layout.butom,null)
            layout.addView(newPicB)
            picB = layout.findViewById(R.id.imageButton2)
            val picBCard = picB.findViewById<ImageButton>(R.id.imageButton2)
            picBCard.setOnClickListener {
                openGalleryForImage()
            }
        }

    }
    private fun addPictures(){
        val picString = pref.getString("pictures","")

        var pic = JSONArray()
        if (picString != ""){
            pic = JSONArray(pref.getString("pictures",""))
        }
        val container = findViewById<GridLayout>(R.id.container)
        for(i in 0 until pic.length()){
            val item: JSONObject = pic.getJSONObject(i)
            val newView = layoutInflater.inflate(R.layout.photo,null)
            val picture = newView.findViewById<ImageView>(R.id.d3)
            val picTime = newView.findViewById<TextView>(R.id.d1)
            picture.setImageURI(Uri.parse(item.getString("uri")))
            picTime.text = item.getString("time")
            container.addView(newView)
        }
        val picB = layoutInflater.inflate(R.layout.butom,null)
        container.addView(picB)
        val picButton = container.findViewById<ImageButton>(R.id.imageButton2)
        picButton.setOnClickListener {
            openGalleryForImage()
        }
    }
    private fun setText(key: String,text: String){
        val ed = pref.edit()
        ed.putString(key,text)
        ed.apply()
    }
    private fun openGalleryForImage(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent,REQUEST_CODE)
    }

    private fun clickListen(){
        findViewById<View>(R.id.button5).also {
            it.setOnClickListener {
                startActivity(Intent(this,Menu::class.java))
            }
        }
        findViewById<TextView>(R.id.button6).also {
            it.setOnClickListener {
                val ed = pref.edit()
                ed.putString("pass","")
                ed.apply()
                startActivity(Intent(this,MainActivity3::class.java))
                finishAffinity()
            }
        }
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("enter", MODE_PRIVATE)
        setContentView(R.layout.activity_prof)
        addPictures()
        clickListen()

    }
    fun gophoto(view: View){
        startActivity(Intent(this,photo::class.java))
    }
    }









