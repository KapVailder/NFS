package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.network.urls
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        requestQueue = Volley.newRequestQueue(this)


        register2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        Reg.setOnClickListener {
            val intent = Intent(this, Screen::class.java)
            startActivity(intent)

            val name = Name.text.toString()
            val last = Last.text.toString()
            val email = Email.text.toString()
            val pass = pass.text.toString()
            val rpass = rpass.text.toString()


            val requestJson = JSONObject()
                    .put("firstName", name)
                    .put("lastName", last)
                    .put("email", email)
                    .put("password", pass)

            val request = JsonObjectRequest(
                    Request.Method.POST,
                    urls.REGISTER_URL,
                    requestJson,
                    { response ->
                        Toast.makeText(this, "${response["token"]}", Toast.LENGTH_SHORT).show()
                    },

                    { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        error.printStackTrace()

                    }
            )

            requestQueue.add(request)

            if (name == "" || email == "" || pass == "" || rpass == "") {
                Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}