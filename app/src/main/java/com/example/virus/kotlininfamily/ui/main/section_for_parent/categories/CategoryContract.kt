package com.example.virus.kotlininfamily.ui.main.section_for_parent.categories

import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
interface CategoryContract{
interface View : IProgressBar, IResult<List<Categories>> {
}
    interface Presenter{
        fun getCategoriesById(id:Int)
    }
}
