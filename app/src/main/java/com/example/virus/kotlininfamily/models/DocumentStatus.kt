package com.example.virus.kotlininfamily.models

import java.io.Serializable

public class DocumentStatus ( var id:Int,var comment:String,var status:Int,
                              var family_correct :Boolean,var income_correct :Boolean,
                              var residence_correct :Boolean,
                             var criminal_correct : Boolean,
                              var health_corrent:Boolean,
                              var job_correct:Boolean,
                              var res_correct:Boolean,
                              var bio_correct:Boolean
                              ) :Serializable