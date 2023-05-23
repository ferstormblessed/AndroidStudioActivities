package com.example.actividad1_navegacionentreactividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityEditableText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityEditableText = findViewById(R.id.editTextTextPersonName)
    }

    fun changeActivity(view : View?){
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("userName", mainActivityEditableText.text.toString())

        startActivity(intent)
    }
}