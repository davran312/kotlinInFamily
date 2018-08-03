package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.specialist_article.*

class SpecialistArticleActivity : BaseActivity(), SpecialistArticleContract.View {


    private lateinit var specialistArticle: SpecialistArticle
    private var presenter: SpecialistArticlePresenter? = null

    private var long: String? = null
    private var lat: String? = null
    private var labelLocation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specialist_article)
        init()

    }

    private fun init() {
        presenter = SpecialistArticlePresenter(this)
        presenter!!.getSpecialistArticleById(intent.getIntExtra(Const.EXTRA_SPECIALIST_ID, 0))
        initListeners()

    }

    private fun initListeners() {
        phone.setOnClickListener {
            if (specialistArticle.contacts!![2].value != null) {
                dialContactPhone(specialistArticle.contacts!![2].value.toString())
            } else {
                Toast.makeText(this, "Нет номера", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onSuccess(result: SpecialistArticle) {

        specialistArticle = result

        setDataOnViews()
    }

    private fun setDataOnViews() {
        name.text = specialistArticle.name
        tw_adress.text = specialistArticle.address
        if (specialistArticle.photo != null) {
            Glide.with(this).load(specialistArticle.photo).into(profile_image)
        } else {
            Glide.with(this).load(R.drawable.default_profile_image).into(profile_image)
        }


        tw_schedule.text = specialistArticle.schedule
        tw_descrption.text = specialistArticle.description
        tw_contact_phone.text = specialistArticle.contacts?.get(2)?.value
        labelLocation = specialistArticle.address.toString()
        long = specialistArticle.longitude.toString()
        lat = specialistArticle.latitude.toString()
    }

    fun maps(view: View) {
        var uri = Uri.parse("geo:<" + lat + ">,<" + long + ">?q=<" + lat + ">,<" + long + ">(" + labelLocation + ")0")
        var intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        if (long != null && lat != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Нет адреса", Toast.LENGTH_SHORT).show()
        }
    }


    private fun dialContactPhone(phoneNumber: String) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
    }
}