package ar.utn.frba.mobile.plantis.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentDrPlantisBinding
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchBinding

class DrPlantisFragment : Fragment() {

    lateinit var binding: FragmentDrPlantisBinding
    val REQUEST_IMAGE_CAPTURE = 1888


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrPlantisBinding.inflate(inflater, container, false)
        binding.drplantisSearchButton.setOnClickListener{
            dispatchTakePictureIntent()
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.drplantisNextButton.setOnClickListener { //TODO
           // val action = R.id.
            findNavController().navigate(action)
        }
    }


    @Suppress("DEPRECATION")
    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.drplantisSearchImageView.setImageBitmap(imageBitmap)
            binding.drplantisNextButton.setVisibility(View.VISIBLE)
        }
    }
}