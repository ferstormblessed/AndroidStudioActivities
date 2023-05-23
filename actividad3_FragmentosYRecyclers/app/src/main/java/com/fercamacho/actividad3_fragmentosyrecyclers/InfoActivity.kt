package com.fercamacho.actividad3_fragmentosyrecyclers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    lateinit var nameText : TextView
    lateinit var priceText : TextView
    lateinit var amountText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        nameText = findViewById(R.id.textView1)
        priceText = findViewById(R.id.textView2)
        amountText = findViewById(R.id.textView3)

        var names  = intent.getStringArrayExtra("nombres")
        var precios = intent.getStringArrayExtra("precios")
        var cantidad = intent.getStringArrayExtra("cantidad")
        var position = intent.getIntExtra("position", 0)

        if(names != null){
            nameText.text = names[position].toString()
        }
        if(precios != null){
            priceText.text = precios[position].toString()
        }
        if(cantidad != null){
            amountText.text = cantidad[position].toString()
        }

    }

    fun goBack(view: View?){
        finish()
    }
}