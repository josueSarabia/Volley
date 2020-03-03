package com.example.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RandomUserViewModel
    private var userList = mutableListOf<RandomUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)

        viewModel.getUsers().observe(this, Observer{ users ->
            run{
                userList = users as MutableList<RandomUser>
                Log.d("VideoVolleyLifeData", " userList size " + userList.size)
            }
        })

        button.setOnClickListener{
            // VolleySingleton.getInstance(this).addToRequestQueue(getStringRequest())
            // VolleySingleton.getInstance(this).addToRequestQueue(getJsonObjectRequest())

            viewModel.addUser()
        }
    }

    /*fun getStringRequest() : StringRequest{

        val url = "http://www.google.com"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                textView.text = response.toString()
            },
            Response.ErrorListener{
                textView.text = "error"
            }
        )

        return stringRequest
    }*/
    /*
    fun getJsonObjectRequest() : JsonObjectRequest{

        val url = "https://randomuser.me/api/?inc=gender,name&results=5"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
               // parseObject(response)
                parseObjectG(response)
            },
            Response.ErrorListener{
                textView.text = "error"
            }
        )

        return jsonObjectRequest
    }*/
    /*
    // obtener json manualmente
    fun parseObject(response: JSONObject){
        val jsonArrayResults: JSONArray  = response.getJSONArray("results")
        val size: Int = jsonArrayResults.length()
        val i: Int = 0
        for (i in 0..size - 1){
            val userObject = jsonArrayResults.getJSONObject(i)
            val gender = userObject.getString("gender")
            val nameObject = userObject.getJSONObject("name")
            val firstName = nameObject.getString("first")
            Log.d("JSONParsing", gender + " " + firstName)
        }

    } */

    /*
    // obtener json ya transformado en un objeto
    //NO FUNCIONA LA CLASE RandomUser NO ESTA COMPLETA
    fun parseObjectG(response: JSONObject){
       var list = RandomUser.getUser(response)
        val i: Int = 0
        val size: Int = list.size
        for ( i in 0.. size - 1){
            val user = list.get(i)
            Log.d("WebJson", "element " + user.name.first)
        }
    } */

}
