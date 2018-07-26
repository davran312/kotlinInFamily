package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.sections

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.virus.kotlininfamily.R
import kotlinx.android.synthetic.main.section_first.*

class SectionSecond : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.section_first,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = getString(R.string.text_adopt)
    }
}