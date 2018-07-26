package com.example.virus.kotlininfamily.ui.main.section_for_child.specialists


import com.example.virus.kotlininfamily.models.SpecialistList
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
interface SpecialistContract{
interface View : IProgressBar, IResult<List<SpecialistList>> {
}
    interface Presenter{
        fun getSpecialistList()
    }
}
