package com.pawel.mymvi.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pawel.mymvi.R

class DogsFactsAdapter(
    private val dogsFacts: ArrayList<String>
) : RecyclerView.Adapter<DogsFactsAdapter.DogsFactViewHolder>() {

    class DogsFactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsFactViewHolder {
        val viewH = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout, parent,
            false
        )
        return DogsFactViewHolder(viewH)
    }

    override fun onBindViewHolder(holder: DogsFactViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return dogsFacts.size
    }
}