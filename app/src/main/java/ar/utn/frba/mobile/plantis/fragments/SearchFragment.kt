package ar.utn.frba.mobile.plantis.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    var imageView: ImageView? = null
    var button: Button? = null
    var search_button: Button? = null
    val REQUEST_IMAGE_CAPTURE = 1888

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        imageView = binding.root.findViewById(R.id.searchImageView)
        button = binding.root.findViewById(R.id.search_button)
        search_button = binding.root.findViewById(R.id.next_button)
        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dispatchTakePictureIntent()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            val action = R.id.action_fragment_search_to_fragment_search_results
            findNavController().navigate(action)
        }
    }


    @Suppress("DEPRECATION")
    private fun dispatchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(imageBitmap)
            search_button?.setVisibility(View.VISIBLE)
        }
    }
}