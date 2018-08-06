package com.example.virus.kotlininfamily.ui.main.section_for_child.specialistNames

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Specialist
import kotlinx.android.synthetic.main.item_category.view.*

class SpecialistNamesAdapter (private var item:List<Specialist> , var listener: Listener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialist_name,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder:RecyclerView.ViewHolder,position:Int){
        (holder as? ViewHolder)?.bind(position)
    }
    override fun getItemCount():Int = item.size

    inner class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.titleView.text = item.get(position).name

            if(item[position].photo != null) {
                var urlPhoto = changeUrlOfPhoto(item[position].photo)
                Glide.with(itemView).load(urlPhoto).into(itemView.imageView)

            }else{

                Glide.with(itemView).load(R.drawable.default_profile_image).into(itemView.imageView)

            }
            itemView.tag = position
            itemView.setOnClickListener{v->
                val index = v.tag as Int
                listener.onItemSelectedAt(index)
            }
        }}

    private fun changeUrlOfPhoto(photoUrl: String?): String {
        var res = "http://165.227.147.84:5678" + photoUrl
        return res
    }

    interface Listener{
        fun onItemSelectedAt(position:Int)
    }
}

