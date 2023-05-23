package com.fercamacho.actividad3_fragmentosyrecyclers

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.icu.text.IDNA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "request"
    private lateinit var queue : RequestQueue
    lateinit var recyclerView : RecyclerView
    lateinit var nombres : ArrayList<String>
    lateinit var precios : ArrayList<String>
    lateinit var amount : ArrayList<String>
    lateinit var adapter : ItemAdapter
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        nombres = ArrayList()
        precios = ArrayList()
        amount = ArrayList()

        recyclerView = findViewById(R.id.recyclerView)

        context = applicationContext;
    }

    fun loadJSON(view: View?){
        queue = Volley.newRequestQueue(this)

        var url = "https://raw.githubusercontent.com/paolag14/Actvidad-4/main/test.json"

        var stringRequest = JsonArrayRequest (
            Request.Method.GET,
            url,
            null,
            { response ->
                for(i in 0 until response.length()){

                    val actual = response.getJSONObject(i)
                    Log.wtf("JSON-REQUEST", actual.getString("name"))
                    val name = actual.getString("name")
                    nombres.add(name.toString())

                    Log.wtf("JSON-REQUEST", actual.getString("price"))
                    val price = actual.getString("price")
                    precios.add(price.toString())

                    Log.wtf("JSON-REQUEST", actual.getString("amount"))
                    val cantidad = actual.getString("amount")
                    amount.add(cantidad.toString())
                }
                adapter = ItemAdapter(nombres, precios, amount, this)
                val llm = LinearLayoutManager(this)
                llm.orientation = LinearLayoutManager.VERTICAL

                recyclerView.layoutManager = llm
                recyclerView.adapter = adapter
            },
            { error ->
                Toast.makeText(
                    this,
                    "error $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply {
            tag = TAG
        }

        queue.add(stringRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll(TAG)
    }

    override fun onClick(row: View) {
        val position = recyclerView.getChildLayoutPosition(row)



    }
}