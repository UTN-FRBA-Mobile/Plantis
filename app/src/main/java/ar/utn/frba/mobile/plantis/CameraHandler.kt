package ar.utn.frba.mobile.plantis

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.*
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class CameraHandler (
    image: ImageView, nextButton: Button,
    registerForActivityResult: (contract: StartActivityForResult, callback: ActivityResultCallback<ActivityResult>) -> ActivityResultLauncher<Intent>
) {
    private val resultLauncher = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            image.setImageBitmap(imageBitmap)
            nextButton.visibility = View.VISIBLE
        }
    }

    fun launchTakePictureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(cameraIntent)
    }
}