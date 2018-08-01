package com.example.virus.kotlininfamily.ui.main.section_for_child.specialists
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.SpecialistList
import kotlinx.android.synthetic.main.item_category.view.*

class SpecialistAdapter (private var list: List<SpecialistList>, var listener: Listener,
                         var imageList:ArrayList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder:RecyclerView.ViewHolder,position:Int){
        (holder as? ViewHolder)?.bind(position)
    }
    override fun getItemCount():Int = list.size

    inner class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.titleView.text = list.get(position).field
            Glide.with(itemView).load(imageList.get(position)).into(itemView.imageView)
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
