package ar.utn.frba.mobile.plantis.fragments

import android.graphics.Bitmap
import android.opengl.Visibility
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ar.utn.frba.mobile.plantis.CameraHandler
import ar.utn.frba.mobile.plantis.MainActivity
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.client.PlantId
import ar.utn.frba.mobile.plantis.client.PlantIdMock
import ar.utn.frba.mobile.plantis.client.PlantIdUtils.sendPostRequest
import ar.utn.frba.mobile.plantis.client.Suggestion
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchBinding
import ar.utn.frba.mobile.plantis.viewModels.ApiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var model: ApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        model = ApiViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setTopBarTitle("Search Plant")
        (activity as MainActivity).hideNavigationIcon()

        val cameraHandler = CameraHandler(::afterTakingPhoto, ::registerForActivityResult)

        binding.searchButton.setOnClickListener { cameraHandler.launchTakePictureIntent() }
        binding.anotherPhotoButton.setOnClickListener { cameraHandler.launchTakePictureIntent() }
        binding.nextButton.setOnClickListener { goToSearchResults(cameraHandler.lastImageBitmap) }
    }

    private fun afterTakingPhoto(photoBitmap: Bitmap) {
        binding.searchImageView.setImageBitmap(photoBitmap)
        binding.startSearchLayout.visibility = View.GONE
        binding.resultsLayout.visibility = View.VISIBLE

    }

    private fun goToSearchResults(lastImage: Bitmap) {

        fetch(lastImage)

    }

    private fun fetch(lastImage: Bitmap) {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val result = PlantId().identifyPlantFromImage(lastImage)
                // Parse result string JSON to data class
                withContext(Dispatchers.Main) {
                    // Update view model
                    model.suggestions = result
                    // Quiza acá iria una pantalla loading
                    val wantsToAddPlant = arguments?.getBoolean("wantsToAddPlant")
                    val bundle = bundleOf(
                        "suggestions" to model.suggestions!!,
                        "wantsToAddPlant" to true
                    )
                    val action = R.id.action_fragment_search_to_fragment_search_results
                    findNavController().navigate(action, bundle)

                }
            } catch (err: Error) {
                print("Error when parsing JSON: " + err.localizedMessage)
            }
        }
    }

}