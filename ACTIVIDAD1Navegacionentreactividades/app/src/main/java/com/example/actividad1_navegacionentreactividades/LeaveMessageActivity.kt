package com.example.actividad1_navegacionentreactividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LeaveMessageActivity : AppCompatActivity() {

    private lateinit var leaveMessageActivityEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_message)

        leaveMessageActivityEditText = findViewById(R.id.messageEditText)
    }

    fun backToMenu(view : View?){
        //val intent = Intent()
        Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()

        finish()
    }
}