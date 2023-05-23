package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // view binding - asocia los elementos de una vista conn un objeto
        //de kotlin para agilizar acceso y reducir errores
    }

    fun registro(view: View?){

        var loginData = binding.login.text.toString()
        var passwordData = binding.password.text.toString()
        var authTask = Firebase.auth.createUserWithEmailAndPassword(loginData, passwordData)

        authTask.addOnCompleteListener(this){ resultado ->

            if(resultado.isSuccessful){

                Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
            } else {

                Log.wtf("Firebase","ERROR: ${resultado.exception?.message}")
                Toast.makeText(this, "ERROR: ${resultado.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(view: View?){

        var loginData = binding.login.text.toString()
        var passwordData = binding.password.text.toString()

        var authTask = Firebase.auth.signInWithEmailAndPassword(loginData, passwordData)

        authTask.addOnCompleteListener(this){ resultado ->

            if(resultado.isSuccessful){
                Toast.makeText(this, "LOGIN EXITOSO", Toast.LENGTH_SHORT).show()
            } else{
                Log.wtf("FIREBASE", "ERROR: ${resultado.exception?.message}")
            }
        }
    }

    fun logout(view: View?){

        Firebase.auth.signOut()
        Toast.makeText(this, "LOG OUT EXITOSO", Toast.LENGTH_SHORT).show()
    }

    override fun onStart(){
        //verificar validez de usuario onStart
        //para asegurarse que siga siendo util
        super.onStart()
        verificarUsuario()
    }

    fun verificarUsuario(){
        if(Firebase.auth.currentUser == null){
            Toast.makeText(this, "SIN USUARIO", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, Firebase.auth.currentUser?.email, Toast.LENGTH_SHORT).show()
        }
    }

    fun verificarUsuarioGUI(view: View?){
        verificarUsuario()
    }

    fun registrarDatos(view : View?){
        val perrito = hashMapOf(
            "nombre" to binding.nombre.text.toString(),
            "edad" to binding.edad.text.toString()
        )

        val collection: CollectionReference = Firebase.firestore.collection("perritos")
        val taskAdd = collection.add(perrito)

        taskAdd.addOnSuccessListener { documentReference ->
            Toast.makeText(this, "id: ${documentReference.id}", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "ERROR AL GUARDADO", Toast.LENGTH_SHORT).show()
            Log.e("FIRESTORE", "error: $error")
        }
    }

    fun solicitarDatos(view: View?){
        val collection: CollectionReference = Firebase.firestore.collection("perritos")



        val queryTask = collection.get()

        queryTask.addOnSuccessListener { result ->
            Toast.makeText(this, "QUERY EXITOSO", Toast.LENGTH_SHORT).show()

            for(documentoActual in result){
                Log.d("FIRESTORE", "${documentoActual.id} ${documentoActual.data}")
            }
        }.addOnFailureListener{ error ->
            Log.e("FIRESTORE", "error en query: $error")
        }

        collection.addSnapshotListener{ datos, e ->
            if(e != null){
                Log.e("FIRESTORE", "error: $e")
                return@addSnapshotListener
            }

            for(cambios in datos!!.documentChanges){
                when(cambios.type){
                    DocumentChange.Type.ADDED -> Log.d("REALTIME", "agregado: ${cambios.document.data}")
                    DocumentChange.Type.MODIFIED -> Log.d("REALTIME", "modificado: ${cambios.document.data}")
                    DocumentChange.Type.REMOVED -> Log.d("REALTIME", "removido: ${cambios.document.data}")
                }
            }
        }
    }

}