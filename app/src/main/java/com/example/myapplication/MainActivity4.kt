package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.myhope.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity4 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        viewAS()
        viewFE()
    }
    private fun viewAS(){
        val url = "http://mskko2021.mad.hakta.pro/api/quotes"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    val jsonObject = response.getJSONArray("data")
                    val container = findViewById<LinearLayout>(R.id.con)
                    for (i in 0 until jsonObject.length()){
                        val jsonObject1 = jsonObject.getJSONObject(i)
                        val title = jsonObject1.getString("title")
                        val image = jsonObject1.getString("image")
                        val desc = jsonObject1.getString("description")
                        val view = layoutInflater.inflate(R.layout.card,null,false)
                        val vtitle = view.findViewById<TextView>(R.id.d1)
                        val vdesk = view.findViewById<TextView>(R.id.d2)
                        val vimag = view.findViewById<ImageView>(R.id.d3)
                        vtitle.text = title
                        vdesk.text = desc
                        Glide.with(this).load(image).into(vimag)
                        container.addView(view)

                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error -> error.printStackTrace() }
        requestQueue.add(jsonObjectRequest)
    }
    private fun viewFE(){
        val url = "http://mskko2021.mad.hakta.pro/api/feelings"
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    var jsonObject = response.getJSONArray("data")
                    jsonObject = sort(jsonObject)
                    val container = findViewById<LinearLayout>(R.id.cont)
                    for (i in 0 until jsonObject.length()){
                        val jsonObject1 = jsonObject.getJSONObject(i)
                        val view = layoutInflater.inflate(R.layout.feelcard,null,false)
                        view.findViewById<TextView>(R.id.d1).text = jsonObject1.getString("title")
                        Glide.with(this).load(jsonObject1.getString("image")).into(view.findViewById<ImageView>(R.id.d3))
                        container.addView(view)
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error -> error.printStackTrace() }
        requestQueue.add(jsonObjectRequest)
    }
    private fun sort(arr: JSONArray): JSONArray{
        val jsonValue = ArrayList<JSONObject>()
        for(i in 0 until arr.length()){
            jsonValue.add(arr.getJSONObject(i))
        }
        jsonValue.sortWith(compareBy {it.getInt("position")})
        return JSONArray(jsonValue)
    }
    fun gnome (view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
    fun goProf (view: View){
        val intent = Intent(this, Prof::class.java)
        startActivity(intent)
    }
}