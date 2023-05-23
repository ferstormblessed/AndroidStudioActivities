package com.fercamacho.mapitas

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.fercamacho.mapitas.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.util.jar.Manifest
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationSettingsRequest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val PERMISO_UBICACION = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var solicitud = LocationRequest.create().apply{
            interval = 10 * 1000
            fastInterval = 2 * 1000
        }

        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED){
            LocationServices.
                getFusedLocationProviderClient(this).
                requestLocationUpdates(solicitud, {
                    object: LocationCallback(){
                        override fun onLocationResult(p0: LocationResult) {
                            super.onLocationResult(p0)
                        }
                        },
                        Looper.myLooper()
                    )
                }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val edificioIngenieria = LatLng(20.737030, -103.454188)
        mMap.addMarker(MarkerOptions().position(edificioIngenieria).title("Estoy aqui"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edificioIngenieria, 30f))

        mMap.setOnMapClickListener { latLang ->
            mMap.addMarker(
                MarkerOptions()
                    .position(latLang)
                    .title("Marcador creado dinamicamente")
                    .alpha(0.5f)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_ORANGE
                    )
                )
            )

        }
        habilitarMyLocationn()

    }

    fun habilitarMyLocationn(){
        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }
        else {
            val permisos = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permisos, PERMISO_UBICACION)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISO_UBICACION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }
    }

}