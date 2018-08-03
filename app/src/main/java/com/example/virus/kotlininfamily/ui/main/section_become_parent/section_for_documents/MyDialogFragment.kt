package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
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
import com.bumptech.glide.Glide
import com.example.virus.kotlininfamily.BuildConfig
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.data.Permissions
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.example.virus.kotlininfamily.utils.error_log.FileLog
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import java.io.File


/**
 * Created by admin on 7/31/18.
 */
class MyDialogFragment : DialogFragment() {

    private var documentName: String? = null
    private var imagePath: String? = null
    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_dialog, container)

        getDataFromBundle()
        init()
        checkTheStateOfImagePhoto()

        return v
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

        v.image_photo.setOnClickListener {
            showPickDialogItem()
        }

        v.submit_photo.setOnClickListener {
            if (!TextUtils.isEmpty(imagePath)) {
                Log.d("Quantity: ", imagePath)
                val callingActivity = activity as DocumentsActivity?
                callingActivity!!.onGetDataFromDialog(imagePath!!)
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

    private fun showPickDialogItem() {
        val args = arrayOf(getString(R.string.pick_photo_from_camera), getString(R.string.pick_photo_from_gallery))
        AlertDialog.Builder(v.context)
                .setItems(args, { dialog, w ->
                    if (w == 0)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == 989) {
                imagePath = FileUtils.getImagePathFromInputStreamUri(StartApplication.INSTANCE, data.data)
            }
            if (requestCode == 2) {
                val uri = FileUtils.getPickImageResultUri(v.context, data, imagePath)
                imagePath = FileUtils.getNormalizedUri(v.context, uri).path
            }
            showImage()
        }
    }

    private fun takePhotoFromCamera() {
        if (Permissions.iPermissionCamera(activity as AppCompatActivity)) {
            imagePath = System.nanoTime().toString()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileUtils.getCaptureImageOutputUri(
                    v.context, imagePath)
            if (uri != null) {
                val file = File(uri.path)
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            FileProvider.getUriForFile(
                                    v.context, BuildConfig.APPLICATION_ID + ".provider", file))
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } else
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(intent, 2)
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
        Glide.with(this)
                .load(
                        if (exist) imagePath
                        else R.drawable.default_image256px)
                .into(v.image_photo)

        if (!exist)
            FileLog.showError(v.context, "Произошла ошибка")
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