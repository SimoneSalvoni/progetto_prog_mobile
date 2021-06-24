package com.example.italian_englishgames.memory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.italian_englishgames.R


public class GridAdapter(var context: Context, val dataSet: List<MemCard>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var back = view.findViewById<ImageView>(R.id.card_back)
        var front = view.findViewById<TextView>(R.id.card_front)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var word = dataSet.get(position).word
        /* Potrebbe essere superfluo
        viewHolder.back.setImageResource(R.drawable.cardback) */
        viewHolder.front.text = word

    }
    override fun getItemCount() = dataSet.size

    fun getItem(position: Int): MemCard{
        return dataSet[position]
    }


}

