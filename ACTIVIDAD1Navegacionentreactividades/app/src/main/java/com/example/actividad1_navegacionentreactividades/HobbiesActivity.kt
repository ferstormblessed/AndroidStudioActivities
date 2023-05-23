package com.example.actividad1_navegacionentreactividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class HobbiesActivity : AppCompatActivity() {

    private lateinit var hobbiesActivityEditableText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies)

        hobbiesActivityEditableText = findViewById(R.id.hobbyEditText)
    }


    fun backToMenuHobby(view : View?){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("hobby", hobbiesActivityEditableText.text.toString())

        startActivity(intent)
    }

}