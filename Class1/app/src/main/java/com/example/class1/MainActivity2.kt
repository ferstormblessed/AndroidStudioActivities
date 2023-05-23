package com.example.class1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    private lateinit var nombre : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "edad: ${intent.getFloatExtra("edad", 0f)}", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "calificacion: ${intent.getIntExtra("calificacion", 0)}", Toast.LENGTH_SHORT).show()

        nombre = findViewById(R.id.actividad2Nombre)

    }

    fun regresar(view: View?){

        val intent = Intent()
        intent.putExtra("resultadoNombre", nombre.text.toString())
        intent.putExtra("hora", 8)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }
}