package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.network.urls
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)


        signIN.setOnClickListener {
            val login = login.text.toString()
            val password = password.text.toString()

            val request = JsonObjectRequest(Request.Method.POST,
                    urls.LOGIN_URL,
                    JSONObject()
                            .put("email", login)
                            .put("password", password),

                    {
                        Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show()
                    },

                    {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            )

            requestQueue.add(request)
        }
        register.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }
    }
}