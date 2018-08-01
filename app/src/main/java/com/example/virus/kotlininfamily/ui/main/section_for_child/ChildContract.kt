package com.example.virus.kotlininfamily.ui.main.section_become_parent

import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
import java.util.*
import kotlin.collections.ArrayList

interface ChildContract {
    interface View: IProgressBar, IResult<List<Categories>>
    interface Presenter{
        fun getMainMenuCategoryArticles(id:Int)

    }




    }
