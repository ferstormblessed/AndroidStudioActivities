package com.fercamacho.fragmentosrecycler

import android.content.Context
import android.icu.util.BuddhistCalendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.lang.RuntimeException
import java.nio.BufferUnderflowException

//Llaves para guardar datos parametrizados (llegan de afuera)
private const val NOMBRE = "nombre"
private const val DIRECCION = "direccionn"

/**
 * A simple [Fragment] subclass.
 * Use the [PizzaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PizzaFragment : Fragment() {
    private var nombre: String? = null
    private var direccion: String? = null
    private var listener: Callback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(NOMBRE)
            direccion = it.getString(DIRECCION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(
            R.layout.fragment_pizza,
            container,
            false
        )

        view.findViewById<TextView>(R.id.namePizza).apply {
            text = nombre
        }
        view.findViewById<TextView>(R.id.directionPizza).apply {
            text = direccion
        }

        view.findViewById<Button>(R.id.pizzaFragmentInvocation).setOnClickListener{
            listener?.ejecutar()
        }

        return view
    }

    interface Callback{
        fun ejecutar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if(context is Callback){
            context
        }else{
            throw RuntimeException("ACTIVIDAD DEBE IMPLEMENTAR INTERFAZ CALLBACK")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(nombre: String, direccion: String): PizzaFragment {
            val result = PizzaFragment()
            val bundle = Bundle()
            bundle.putString(NOMBRE, nombre)
            bundle.putString(DIRECCION, direccion)
            result.arguments = bundle

            return result
        }
//            PizzaFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}