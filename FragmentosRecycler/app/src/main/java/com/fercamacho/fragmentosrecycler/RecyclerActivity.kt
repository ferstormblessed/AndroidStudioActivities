package com.fercamacho.fragmentosrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var datos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        datos = ArrayList()
        datos.add("Fifi")
        datos.add("Firulais")
        datos.add("Fido")

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = PerritoAdapter(datos)

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        val glm = GridLayoutManager(this, 2)

        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }
}