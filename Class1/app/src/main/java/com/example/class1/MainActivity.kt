package com.example.class1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var textitoEditable : EditText

    val lanzador = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ){ result ->

        Toast.makeText(this, "REGRESANDO DE ACTIVIDAD", Toast.LENGTH_SHORT).show()

        if(result.resultCode == Activity.RESULT_OK){

            val datos = result.data

            Toast.makeText(this, datos?.getStringExtra("resultadoNombre"), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "hora: ${ datos?.getIntExtra("hora", -1) }", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // acceder a un elemento de la GUI
        var textito = findViewById<TextView>(R.id.textView)
        textitoEditable = findViewById<EditText>(R.id.editTextTextPersonName)
        var botoncito = findViewById<Button>(R.id.button)

        botoncito.text = "Pruebita"
        var textoDelBoton = textitoEditable.text

        botoncito.setOnClickListener {
            (it as Button).text = "AH OK"
            Toast.makeText(this, textoDelBoton, Toast.LENGTH_LONG).show()
        }
    }

    fun boton2(view: View?){
        Toast.makeText(this, "PRESIONASTE BOTON", Toast.LENGTH_SHORT).show()
    }

    fun cambiarActividad(view: View?){
        val intent = Intent(this, MainActivity2::class.java)

        // como mandar datos a la nueva actividad
        intent.putExtra("name", textitoEditable.text.toString())
        intent.putExtra("edad", 20.3f)
        intent.putExtra("calificacion", 40)

        //startActivity(intent)

        lanzador.launch(intent)
    }
}