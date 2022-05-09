package ar.utn.frba.mobile.plantis.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    var imageView: ImageView? = null
    var button: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        imageView = binding.root.findViewById(R.id.searchImageView)
        button = binding.root.findViewById(R.id.buttonSearch)
        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dispatchTakePictureIntent()

            }
        })
        return binding.root
    }

    val REQUEST_IMAGE_CAPTURE = 1888

    @Suppress("DEPRECATION")
    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        System.out.println(requestCode)
        System.out.println(resultCode)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            System.out.println("Boton Result!!!")
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(imageBitmap)
        }
    }
}