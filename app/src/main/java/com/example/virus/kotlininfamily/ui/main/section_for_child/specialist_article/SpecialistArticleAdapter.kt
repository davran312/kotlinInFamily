package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle.ArticleAdapter
import kotlinx.android.synthetic.main.specialist_article.view.*

lateinit var long:String
lateinit var lat:String

class SpecialistArticleAdapter(private var item:SpecialistArticle) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.specialist_article,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SpecialistArticleAdapter.ViewHolder)?.bind(position)
    }

    override fun getItemCount(): Int =1


    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.name.text = item.name
            itemView.tw_adress.text = item.address
            //Glide.with(itemView).load(item.photo).into(itemView.img_photo)
            itemView.tw_schedule.text = item.schedule
            itemView.tw_descrption.text = item.description
            itemView.tw_contact_phone.text = item.contactList?.get(2)?.value

            long=item.longitude.toString()
            lat=item.latitude.toString()
        }
        }

    }