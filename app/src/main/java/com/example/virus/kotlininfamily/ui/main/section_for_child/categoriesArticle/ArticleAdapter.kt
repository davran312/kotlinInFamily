package com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import kotlinx.android.synthetic.main.category_article.view.*

class ArticleAdapter (private var item:Categories) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_article,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)?.bind(position)
    }

    override fun getItemCount(): Int =1


    inner class ViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.tw_article_title.text= item.title
            Glide.with(itemView).load(item.image).into(itemView.img_article_image)
            itemView.tw_article_text.text = item.content
        }



}}