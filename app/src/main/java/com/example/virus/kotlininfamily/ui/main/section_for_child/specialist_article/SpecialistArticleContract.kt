package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
interface SpecialistArticleContract{
interface View : IProgressBar, IResult<SpecialistArticle> {
}
    interface Presenter{
        fun getSpecialistArticleById(id:Int)
    }
}
