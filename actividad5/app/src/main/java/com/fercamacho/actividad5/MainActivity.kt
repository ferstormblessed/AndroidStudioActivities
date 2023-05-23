package com.fercamacho.actividad5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeToMaps(view: View?){
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun changeToPhotos(view: View?){
        val intent = Intent(this, PhotosActivity::class.java)
        startActivity(intent)
    }
}