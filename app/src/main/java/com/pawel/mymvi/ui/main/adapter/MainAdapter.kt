package com.pawel.mymvi.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pawel.mymvi.R
import com.pawel.mymvi.data.model.User
import kotlinx.android.synthetic.main.dog_fact_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val items: ArrayList<Any>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_USER = 0
        const val ITEM_DOG_FACT = 1
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    class DogsFactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fact: String) {
            itemView.dogFactText.text = fact
            //itemView.dogListOrder.text = (itemId + 2).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return if (viewType == ITEM_USER) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
            UserViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.dog_fact_layout, parent,
                false
            )
            DogsFactsViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addData(list: List<Any>) {
        items.addAll(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: Any = items[position]
        if (holder is UserViewHolder) {
            holder.bind(item  as User)
        } else if (holder is DogsFactsViewHolder) {
            holder.bind(item as String)
        }
    }

    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        return if (items[position] is User) ITEM_USER else ITEM_DOG_FACT
    }
}