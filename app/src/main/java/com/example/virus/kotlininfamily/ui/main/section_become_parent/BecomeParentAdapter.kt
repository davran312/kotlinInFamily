package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import kotlinx.android.synthetic.main.item_category.view.*

    class BecomeParentAdapter(private var list: List<Categories>, var listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
            return ViewHolder(view)
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position:Int){
            (holder as? ViewHolder)?.bind(position)
        }
        override fun getItemCount():Int = list.size

        inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView){
            fun bind(position:Int){
                itemView.titleView.text = list.get(position).title
                Glide.with(itemView).load(list.get(position).image).into(itemView.imageView)
                itemView.tag = position
                itemView.setOnClickListener{v->
                    val index = v.tag as Int
                    listener.onItemSelectedAt(index)
                }
            }}
        interface Listener{
            fun onItemSelectedAt(position:Int)
        }
    }

