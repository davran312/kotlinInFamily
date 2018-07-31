package com.example.virus.kotlininfamily.ui.main.section_for_child.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import kotlinx.android.synthetic.main.item_for_categories_activity.view.*

class CategoryAdapter(private var listOfCategories: List<Categories>, var listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_categories_activity,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder:RecyclerView.ViewHolder,position:Int){
        (holder as? ViewHolder)?.bind(position)
    }
    override fun getItemCount():Int = listOfCategories.size

    inner class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.article_title_text.text = listOfCategories.get(position).title
            itemView.article_content.text = listOfCategories[position].content
            Glide.with(itemView).load(listOfCategories.get(position).image).into(itemView.image_of_article)

            if(listOfCategories[position].image == null){
                itemView.image_of_article.layoutParams.height =  0
                itemView.image_of_article.requestLayout()
            }

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
