package com.example.actividad2_almacenamientolocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HobbiesActivity : AppCompatActivity() {

    private lateinit var db : DBHelper
    private lateinit var hobbyTextView : TextView
    private lateinit var hobbyEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies)

        hobbyTextView = findViewById(R.id.textViewFriends)
        hobbyEditText = findViewById(R.id.hobbyEditText)
        db = DBHelper(this)

        findAHobby()
    }

    fun findAHobby(){
        hobbyTextView.text = db.findHobby();
    }

    fun saveHobby(view: View?){
        val oldHobby = hobbyTextView.text.toString()
        val newHobby = hobbyEditText.text.toString()

        db.updateHobby(oldHobby, newHobby)
    }





}