package com.example.actividad2_almacenamientolocal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var db : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        loadGreetings()
        findGreeting(0)
    }

    fun loadGreetings(){
        var saludo1 = "Hello! User"
        var saludo2 = "Howdy Fellow Being!"

        db.saveSaludo(saludo1)
        db.saveSaludo(saludo2)
    }

    fun findGreeting(greeting: Int){
        var greeting = db.findSaludo(greeting)
        Toast.makeText(this, "$greeting", Toast.LENGTH_SHORT).show()
    }

    fun changeToHobbiesActivity(view: View?){
        val intent = Intent(this, HobbiesActivity::class.java)
        startActivity(intent)
    }

    fun changeToFriendsActivity(view: View?){
        val intent = Intent(this, FriendsActivity::class.java)
        startActivity(intent)
    }
}