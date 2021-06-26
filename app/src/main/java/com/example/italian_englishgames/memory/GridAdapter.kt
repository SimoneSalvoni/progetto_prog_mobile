package com.example.italian_englishgames.memory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.italian_englishgames.R



public class GridAdapter(var context: Context, val dataSet: List<MemCard>, val listener
    : OnItemClickListener) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_element, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var word = dataSet.get(position).word
        /* Potrebbe essere superfluo
        viewHolder.back.setImageResource(R.drawable.cardback) */
        viewHolder.front.text = word

    }
    override fun getItemCount() = dataSet.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener{

        var front = itemView.findViewById<TextView>(R.id.card_front)
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
        init {
            view.setOnClickListener(this)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun getItem(position: Int): MemCard{
        return dataSet[position]
    }


}

