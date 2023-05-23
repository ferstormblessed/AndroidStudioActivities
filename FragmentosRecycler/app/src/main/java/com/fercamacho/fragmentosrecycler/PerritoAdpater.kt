package com.fercamacho.fragmentosrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PerritoAdpater(var perritos : ArrayList<String>) : RecyclerView.Adapter<PerritoAdpater.PerritoViewHolder> {


    class PerritoViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView) {
        var texto1: TextView
        var texto2: TextView

        init {
            texto1 = itemView.findViewById(R.id.rowTextView1)
            texto2 = itemView.findViewById(R.id.rowTextView2)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return PerritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerritoViewHolder, position: Int) {
        holder.texto1.text = perritos[position]
        holder.texto2.text = perritos[position]
    }

    override fun getItemCount(): Int {
        return perritos.size
    }
}
