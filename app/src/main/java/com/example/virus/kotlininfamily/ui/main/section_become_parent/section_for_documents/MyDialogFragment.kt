package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents


import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import id.zelory.compressor.Compressor
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.BuildConfig
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.data.Permissions
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.example.virus.kotlininfamily.utils.error_log.FileLog
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by admin on 7/31/18.
 */
class MyDialogFragment : DialogFragment() {

    private var documentName: String? = null
    private var imagePath: String? = null
    lateinit var v: View
    private var photoFile: File? = null
    private var timesOnClick: Int = 0
    var photoFromCameraNumber: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_dialog, container)
        getDataFromBundle()
        init()
        checkTheStateOfImagePhoto()

        return v

    }

    override fun onResume() {
        if (timesOnClick == 0) {
            showPickDialogItem()
        }
        timesOnClick++
        super.onResume()
    }


    private fun getDataFromBundle() {

        documentName = arguments!!.getString(Const.INTENT_GET_DOCUMENT)
        v.description_of_document.text = documentName
        imagePath = arguments!!.getString(Const.INTENT_GET_IMAGE)

    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        v.image_photo.setOnClickListener() {
            showPickDialogItem()
        }


        v.submit_photo.setOnClickListener {
            if (!TextUtils.isEmpty(imagePath)) {
                Log.d("Quantity: ", imagePath)
                val callingActivity = activity as DocumentsActivity?
                callingActivity!!.onGetDataFromDialog(imagePath!!)
                timesOnClick = 0
                dialog.dismiss()


            } else {
                FileLog.showError(v.context, "Nothing to save")
            }
        }
    }

    private fun checkTheStateOfImagePhoto() {
        if (!TextUtils.isEmpty(imagePath)) {
            showImage()
        }

    }

    public fun showPickDialogItem() {
        val args = arrayOf<String>(getString(R.string.pick_photo_from_gallery), getString(R.string.pick_photo_from_camera))
        AlertDialog.Builder(v.context)
                .setItems(args, { dialog, w ->
                    if (w == 1)
                        takePhotoFromCamera()
                    else
                        takePhotoFromGallery()
                }).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Permissions.Request.READ_EXTERNAL_STORAGE && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhotoFromGallery()
        } else if (requestCode == Permissions.Request.CAMERA && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED)
            takePhotoFromCamera()
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if(data != null){
            if (requestCode == 989) {
                imagePath = FileUtils.getImagePathFromInputStreamUri(StartApplication.INSTANCE, data    .data)

            } else if (requestCode == Const.CAMERA) {
                val uri = FileUtils.getPickImageResultUri(context, data, imagePath)
                val file = FileUtils.getNormalizedUri(context, uri)
                imagePath= Compressor.getDefault(context).compressToFile(File(file.path)).path

            }
            showImage()
        }}
    }

    private fun takePhotoFromCamera() {
        if(Permissions.iPermissionCamera(activity as AppCompatActivity )){
            imagePath = System.nanoTime().toString()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileUtils.getCaptureImageOutputUri(context,imagePath)
            if(uri != null){
                val file = File(uri.path)
                if(Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity as AppCompatActivity,
                            BuildConfig.APPLICATION_ID + ".provider",file))

                }
                else{
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
                }
                startActivityForResult(intent,Const.CAMERA)
            }
        }
    }




    private fun takePhotoFromGallery() {
        if (Permissions.iPermissionReadStorage(activity as AppCompatActivity)) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 989)
        }
    }

    private fun showImage() {
        val exist = File(imagePath).exists()
        if(exist)
            v?.image_photo.setImageURI(Uri.parse(imagePath))

}


    companion object {

        internal fun newInstance(documentName: String, imagePath: String?): MyDialogFragment {
            val f = MyDialogFragment()

            val args = Bundle()

            args.putString(Const.INTENT_GET_DOCUMENT, documentName)
            args.putString(Const.INTENT_GET_IMAGE, imagePath)


            f.arguments = args

            return f
        }

    }


}