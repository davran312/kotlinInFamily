package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.TestQuestion
import kotlinx.android.synthetic.main.test_simple_list.view.*

class TestAdapter (private var list: Array<String>,
                   var map:HashMap<Int,TestQuestion>,
                   var listener:Listener): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_simple_list,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(position)
    }
    inner class ViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            itemView.titleView.text = list.get(position)

            itemView.btn_yes.setBackgroundResource(
                    if(map.containsKey(position) && map[position]!!.isResult!!)
                         R.color.mainColor
                    else
                        R.color.shadow_view_foreground_color_dark)
            itemView.btn_no.setBackgroundResource(
                    if(map.containsKey(position) && !map[position]!!.isResult!!)
                        R.color.mainColor
                    else
                        R.color.shadow_view_foreground_color_dark)

            itemView.btn_yes.tag = position
            itemView.btn_yes.setOnClickListener{v->
                val index = v.tag as Int
                listener.onItemSelectedAt(index,TestQuestion(list.get(index),true))
            }
            itemView.btn_no.tag = position
            itemView.btn_no.setOnClickListener{v->
                val index = v.tag as Int
                listener.onItemSelectedAt(index,TestQuestion(list.get(index),false))
        }
    }
}
    fun setResult(position:Int,result:TestQuestion){
        if(map.containsKey(position)){
            map[position] = result
        }
        else
            map.put(position,result)
        notifyItemChanged(position)
    }
    interface Listener{
        fun onItemSelectedAt(position:Int,result:TestQuestion)
    }
}