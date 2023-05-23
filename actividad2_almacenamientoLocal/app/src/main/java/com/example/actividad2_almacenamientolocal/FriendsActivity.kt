package com.example.actividad2_almacenamientolocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FriendsActivity : AppCompatActivity() {

    private lateinit var db : DBHelper
    private lateinit var name : EditText
    private lateinit var hobby : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        name = findViewById(R.id.nameFriendsEditText)
        hobby = findViewById(R.id.hobbyFriendsEditText)
        db = DBHelper(this)
    }

    fun save(view: View?){
        var nameSave = name.text.toString()
        var hobbySave = hobby.text.toString()

        db.save(nameSave, hobbySave)
        Toast.makeText(this, "DATOS GUARDADOS CORRECTAMENTE", Toast.LENGTH_SHORT).show()
    }

    fun search(view: View?){
        var nameSearch = name.text.toString()
        var hobbySearch = db.findHobby(nameSearch)

//        Toast.makeText(this, "Name: $nameSearch", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "Hobby found: $hobbySearch", Toast.LENGTH_SHORT).show()

        hobby.setText("$hobbySearch", TextView.BufferType.EDITABLE)
    }

    fun deleteButton(view: View?){
        var nameDelete = name.text.toString()

        if(db.delete(nameDelete) != 0){
            Toast.makeText(this, "DATOS ELIMINADOS EXITOSAMENTE", Toast.LENGTH_SHORT).show()
        }
    }
}