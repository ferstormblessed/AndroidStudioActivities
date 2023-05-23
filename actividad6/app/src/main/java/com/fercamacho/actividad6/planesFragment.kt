package com.fercamacho.actividad6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [planesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class planesFragment : Fragment() {
    val args: planesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_planes, container, false)

        view.findViewById<TextView>(R.id.arg1).text = args.nombre
        view.findViewById<TextView>(R.id.arg3).text = args.carrera

        return view
    }
}