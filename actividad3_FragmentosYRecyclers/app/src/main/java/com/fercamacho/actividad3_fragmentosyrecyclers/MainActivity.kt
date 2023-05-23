package com.fercamacho.actividad3_fragmentosyrecyclers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var dataFragment: DataFragment
    private lateinit var petsDataFragment: pets_data
    private lateinit var emptyFragment: empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name: String = "Fer"
        var age: String = "20"

        dataFragment = DataFragment.newInstance(name, age)
        petsDataFragment = pets_data.newInstance(name, age)
        emptyFragment = empty()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, emptyFragment, TAG_FRAGMENTO)
        transaction.commit()
    }

    fun swapFragmentData(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual != null){
            var newFragment : Fragment = dataFragment

//            if(fragmentoActual != dataFragment){
//                newFragment = dataFragment
//            }

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, newFragment, TAG_FRAGMENTO)
                commit()
            }
        }
    }

    fun swapFragmentPets(view: View?){
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual != null){
            var newFragment : Fragment = petsDataFragment

//            if(fragmentoActual == emptyFragment){
//                newFragment = petsDataFragment
//            }

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, newFragment, TAG_FRAGMENTO)
                commit()
            }
        }
    }

    fun changeToRecycler(view: View?){
       val intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

    companion object{
        private const val TAG_FRAGMENTO = "fragmentito"
    }
}