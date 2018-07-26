package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.BuildConfig
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.data.Permissions
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.example.virus.kotlininfamily.utils.error_log.FileLog
import kotlinx.android.synthetic.main.activity_document_fill.*
import java.io.File

class DocumentFillActivity : BaseActivity(){
    private var imagePath:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_fill)

        documents_title?.text = intent.getStringExtra(Const.INTENT_GET_DOCUMENT)
        imagePath = intent.getStringExtra(Const.INTENT_GET_IMAGE)
        if(!TextUtils.isEmpty(imagePath))
            showImage()
        iw_choose.setOnClickListener{
            showPickDialogItem()
        }

        btn_send.setOnClickListener{
            if(!TextUtils.isEmpty(imagePath)){
                setResult(Activity.RESULT_OK,Intent().putExtra(Const.INTENT_URI_PATH,imagePath))
                finish()
            }else
                FileLog.showError(this,"Nothing to save")
        }

    }
    private fun showPickDialogItem(){
        val args = arrayOf<String>(getString(R.string.pick_photo_from_camera),getString(R.string.pick_photo_from_gallery))
        AlertDialog.Builder(this)
                .setItems(args,{dialog,w->
                    if(w ==0)
                        takePhotoFromCamera()
                    else
                        takePhotoFromGallery()
                }).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == Permissions.Request.READ_EXTERNAL_STORAGE && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            takePhotoFromGallery()
        }
        else if(requestCode == Permissions.Request.CAMERA && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED)
            takePhotoFromCamera()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK && data != null){
            if(requestCode == 989){
                imagePath = FileUtils.getImagePathFromInputStreamUri(StartApplication.INSTANCE,data.data)
            }
            if(requestCode == 2){
                val uri = FileUtils.getPickImageResultUri(this,data,imagePath)
                imagePath = FileUtils.getNormalizedUri(this,uri).path
            }
            showImage()
        }
    }
    private fun takePhotoFromCamera(){
        if(Permissions.iPermissionCamera(this)){
            imagePath = System.nanoTime().toString()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileUtils.getCaptureImageOutputUri(this,imagePath)
            if(uri != null){
                val file = File(uri.path)
                if(Build.VERSION.SDK_INT >=24){
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".provider",file))
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }else
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
                startActivityForResult(intent,2)
            }
        }
    }
    private fun takePhotoFromGallery(){
        if(Permissions.iPermissionReadStorage(this)){
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent,989)
        }
    }
    private fun showImage(){
        val exist = File(imagePath).exists()
        Glide.with(this).load(if(exist) imagePath else R.drawable.paper_clip64px).into(iw_choose)
        if(!exist)
            FileLog.showError(this,"Произошла ошибка")
    }
}