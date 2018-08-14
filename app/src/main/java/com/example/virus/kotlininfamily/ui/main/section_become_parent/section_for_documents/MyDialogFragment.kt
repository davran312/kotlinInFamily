package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.virus.kotlininfamily.BuildConfig
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.data.Permissions
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.example.virus.kotlininfamily.utils.error_log.FileLog
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import java.io.File

class MyDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(documentName: String, imagePath: String?): MyDialogFragment {
            val f = MyDialogFragment()

            val args = Bundle()

            args.putString(Const.INTENT_GET_DOCUMENT, documentName)
            args.putString(Const.INTENT_GET_IMAGE, imagePath)

            f.arguments = args
            return f
        }

    }

    private var documentName: String? = null
    private var imagePath: String? = null
    private var timesOnClick: Int = 0

    private lateinit var v: View

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
            showImage(imagePath!!)
        }
    }

    private fun showPickDialogItem() {
        val args = arrayOf<String>(getString(R.string.pick_photo_from_gallery), getString(R.string.pick_photo_from_camera))
        AlertDialog.Builder(v.context)
                .setItems(args) { dialog, w ->
                    if (w == 1)
                        takePhotoFromCamera()
                    else
                        takePhotoFromGallery()
                }.show()
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Const.CAMERA -> {
                    val uri = FileUtils.getPickImageResultUri(context, data, imagePath)
                    val file = FileUtils.getNormalizedUri(context, uri)
                    val path = Compressor.getDefault(context).compressToFile(File(file.path)).path
                    imagePath = path
                    showImage(path)
                }
                Const.GALLERY -> {
                    if (data != null) {
                        imagePath = FileUtils.getImagePathFromInputStreamUri(StartApplication.INSTANCE, data!!.data)
                        showImage(imagePath!!)
                    }
                }
            }
        }
    }

    private fun takePhotoFromCamera() {
        if (Permissions.iPermissionCamera(activity as AppCompatActivity)) {
            imagePath = System.nanoTime().toString()
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileUtils.getCaptureImageOutputUri(activity, imagePath)
            if (uri != null) {
                val file = File(uri.path)
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            FileProvider.getUriForFile(activity!!,
                                    BuildConfig.APPLICATION_ID + ".provider",
                                    file))
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } else intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

                startActivityForResult(intent, Const.CAMERA)
            }
        }
    }

    private fun takePhotoFromGallery() {
        if (Permissions.iPermissionReadStorage(activity as AppCompatActivity)) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, Const.GALLERY)
        }
    }

    private fun showImage(imgPath: String) {
        val exist = File(imgPath).exists()
        if (exist)
            v.image_photo.setImageURI(Uri.parse(imgPath))
        v.image_photo.setImageURI(Uri.parse(imgPath))
    }


}