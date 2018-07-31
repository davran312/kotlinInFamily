package com.example.virus.infamily.mvp.ui.ui.documents

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import kotlinx.android.synthetic.main.item_for_document_activity.view.*

/**
 * Created by Alier on 20.07.2018.
 */

class DocumentAdapter(private var list: Array<String>,
                      private var map: HashMap<Int, String>,
                      var listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_for_document_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(position)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            itemView.document_name.text = list[position]

            if(map.containsKey(position)) {
                Glide.with(itemView).load(R.drawable.green_tick32px).into(itemView.image_tick)
            }else {
                Glide.with(itemView).load(R.drawable.grey_tick32px).into(itemView.image_tick)
            }

            itemView.tag = position
            itemView.setOnClickListener { v ->
                val index = v.tag as Int
                listener.onItemSelectedAt(index, list.get(index))
            }
        }
    }

    fun setFilledIndex(index: Int, imagePath: String){
        if(!map.containsKey(index)) map.put(index, imagePath)
        else map[index] = imagePath
        notifyItemChanged(index)
    }

    interface Listener {
        fun onItemSelectedAt(index: Int, document: String)
    }
}