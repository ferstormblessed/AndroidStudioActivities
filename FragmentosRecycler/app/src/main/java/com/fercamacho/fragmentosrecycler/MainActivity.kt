package com.fercamacho.fragmentosrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.concurrent.RecursiveAction

class MainActivity : AppCompatActivity(), PizzaFragment.Callback {

    private lateinit var fragmentitoFragmet: FragmentitoFragment
    private lateinit var pizzaFragment: PizzaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentitoFragmet = FragmentitoFragment()
        pizzaFragment = PizzaFragment.newInstance("DOMINOS", "VALLE REAL")

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, pizzaFragment, TAG_FRAGMENTO)
        transaction.commit()
    }

    fun swapFragments(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual != null){
            var newFragment : Fragment = pizzaFragment

            if(fragmentoActual == pizzaFragment){
                newFragment = fragmentitoFragmet
            }

            supportFragmentManager.beginTransaction().apply {
                //manera practica
                replace(R.id.fragmentContainerView, newFragment, TAG_FRAGMENTO)
                //maner impractica
                //remove(fragmentoActual)
                //add(R.id.fragmentContainerView, newFragment, TAG_FRAGMENTO)

                commit()
            }
        }
    }

    fun invocarMetodoEnFragmento(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual == fragmentitoFragmet){
            fragmentitoFragmet.test(this)
        }else{
            Toast.makeText(this, "CAMBIA FRAGMENTO PROFA", Toast.LENGTH_SHORT).show()
        }
    }

    interface Callback{}

    companion object{
        private const val TAG_FRAGMENTO = "fragmentito"
    }

    override fun ejecutar() {
        Toast.makeText(this, "INVOCANDO DESDE FRAGMENTO", Toast.LENGTH_SHORT).show()
    }

    fun recyclerActivity(view: View?){
        val intent = Intent(this, RecursiveAction::class.java)
    }
}