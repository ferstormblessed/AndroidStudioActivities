package com.fercamacho.actividad3_fragmentosyrecyclers

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ItemAdapter(
    var names : ArrayList<String>,
    var prices : ArrayList<String>,
    var amount : ArrayList<String>,
    var listener : View.OnClickListener
    ) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    // definamos una clase interna que va a servir
    // como view holder
    // (piensen en este como algo similar al binding)

    class ItemViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView){

        var texto1 : TextView
        var texto2 : TextView
        var texto3 : TextView
        var boton : Button

        init {

            texto1 = itemView.findViewById(R.id.rowText1)
            texto2 = itemView.findViewById(R.id.rowText2)
            texto3 = itemView.findViewById(R.id.rowText3)
            boton = itemView.findViewById(R.id.rowButton)
        }
    }

    // momento en que creamos la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // igualito a la cuestion del fragment
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        view.setOnClickListener(listener)

        val viewHolder = ItemViewHolder(view)

        viewHolder.boton.setOnClickListener {
            //val intent = Intent(View, InfoActivity::class.java)
            viewHolder.texto3.visibility = View.VISIBLE
            Log.wtf("BOTON INTERNO", "BOTON PRESIONADO")
        }

        return viewHolder
    }

    // asociamos una vista en particular con un elemento de nuestra
    // fuente de datos
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.texto1.text = names[position]
        holder.texto2.text = prices[position]
        holder.texto3.text = amount[position]
    }

    // obtener total de elementos
    override fun getItemCount(): Int {
        return names.size
    }
}