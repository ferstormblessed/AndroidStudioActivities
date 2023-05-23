package com.example.actividad1_navegacionentreactividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val menuActivityHiText = findViewById<TextView>(R.id.textViewName)
        menuActivityHiText.text = "Hi " + intent.getStringExtra("userName")

        val menuActivityHobbyText = findViewById<TextView>(R.id.textViewHobby)
        menuActivityHobbyText.text = intent.getStringExtra("hobby")
    }

    fun changeToHobbiesActivity(view : View?){
        val intent = Intent(this, HobbiesActivity::class.java)
        startActivity(intent)
    }

    fun changeToFriendsActivity(view : View?){
        val intent = Intent(this, FriendsActivity::class.java)
        startActivity(intent)
    }

    fun changeToLeaveMessageActivity(view : View?){
        val intent = Intent(this, LeaveMessageActivity::class.java)
        startActivity(intent)
    }
}