package com.fercamacho.actividad3_fragmentosyrecyclers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val NAME = "name"
private const val AGE = "age"

class pets_data : Fragment() {
    private var name: String? = null
    private var age: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            age = it.getString(AGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(
            R.layout.fragment_pets_data,
            container,
            false
        )

        view.findViewById<TextView>(R.id.namePetFragment).apply {
            text = name
        }
        view.findViewById<TextView>(R.id.agePetFragment).apply {
            text = age
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, age: String): pets_data {
            val result = pets_data()
            val bundle = Bundle()
            bundle.putString(NAME, name)
            bundle.putString(AGE, age)
            result.arguments = bundle

            return result
        }
    }
}