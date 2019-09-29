package myprofile.example.com.daftarmenu.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.fondesa.kpermissions.request.PermissionRequest.AcceptedListener
import com.fondesa.kpermissions.request.PermissionRequest.DeniedListener
import kotlinx.android.synthetic.main.layoutdata.bt
import kotlinx.android.synthetic.main.layoutdata.harga
import kotlinx.android.synthetic.main.layoutdata.image
import kotlinx.android.synthetic.main.layoutdata.nama
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import myprofile.example.com.daftarmenu.R
import myprofile.example.com.daftarmenu.data.MenuDB
import myprofile.example.com.daftarmenu.data.MenuModel
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddFragment:Fragment(),
    PermissionRequest.AcceptedListener,PermissionRequest.DeniedListener {

    override fun onPermissionsAccepted(permissions: Array<out String>) {
        showMessageDialog()
    }

    @RequiresApi(VERSION_CODES.JELLY_BEAN)
    override fun onPermissionsDenied(permissions: Array<out String>) {
        requestPermission()
    }



    companion object {
        fun getInstance(): AddFragment {
            return AddFragment()
        }
    }

    val GALLERY=1
    val CAMERA=2
    var imageData:ByteArray?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.layoutdata
            , container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db=MenuDB.getInstance(context!!)
        image.setOnClickListener {
            checkVersion()
        }
        bt.setOnClickListener{
            simpanData(db)
        }
    }
    private fun simpanData(db:MenuDB?) : Job {
        return GlobalScope.launch{
            db?.menuDao()?.tambahMakanan(MenuModel(
                namaMenu = nama.text.toString(),
                hargaMenu = harga.text.toString(),
                gambarMenu = imageData))
        }
    }

    fun checkVersion() {
        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.M
        ) {
            requestPermission()

        }else{
            showMessageDialog()
        }
    }

    @RequiresApi(VERSION_CODES.JELLY_BEAN)
    private fun requestPermission() {
        val request=permissionsBuilder(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).build()

        request.acceptedListener(this)
        request.deniedListener(this)

        request.send()
    }
    private fun showMessageDialog(){
        val pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Silahkan Pilih")
        val pictureDialogItems = arrayOf(
            "Ambil Foto dari Gallery","Ambil Foto dengan Kamera")
        pictureDialog.setItems(pictureDialogItems) {dialog, which ->
            when (which){
                0 -> pilihGallery()
                1 -> pilihKamera()
            }
        }
        pictureDialog.show()


    }

    private fun pilihKamera() {
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA)
    }

    private fun pilihGallery() {
        val intent= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(activity!!.contentResolver, contentURI)
                    image.setImageBitmap(bitmap)
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream)
                    imageData = stream.toByteArray()

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            image.setImageBitmap(thumbnail)

            val stream = ByteArrayOutputStream()
            thumbnail.compress(Bitmap.CompressFormat.JPEG,50,stream)
            imageData = stream.toByteArray()
        }
    }
}




