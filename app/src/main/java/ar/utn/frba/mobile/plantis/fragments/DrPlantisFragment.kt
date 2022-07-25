package ar.utn.frba.mobile.plantis.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ar.utn.frba.mobile.plantis.CameraHandler
import ar.utn.frba.mobile.plantis.MainActivity
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.client.PlantId
import ar.utn.frba.mobile.plantis.client.healthAssesment.PlantIdHealth
import ar.utn.frba.mobile.plantis.client.healthAssesment.PlantIdHealthMock
import ar.utn.frba.mobile.plantis.databinding.FragmentDrPlantisBinding
import ar.utn.frba.mobile.plantis.viewModels.ApiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrPlantisFragment : Fragment() {
    lateinit var binding: FragmentDrPlantisBinding
    lateinit var viewModel: ApiViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrPlantisBinding.inflate(inflater, container, false)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setTopBarTitle("Dr Plantis")
        (activity as MainActivity).hideNavigationIcon()

        viewModel = ViewModelProvider(requireActivity()).get(ApiViewModel::class.java)

        val cameraHandler = CameraHandler(::afterTakingPhoto, ::registerForActivityResult)

        binding.drplantisSearchButton.setOnClickListener { cameraHandler.launchTakePictureIntent() }
        binding.drplantisNextButton.setOnClickListener { goToHealthResults(cameraHandler.lastImageBitmap) }
        binding.anotherPhotoButton.setOnClickListener { cameraHandler.launchTakePictureIntent() }
    }
    
    fun afterTakingPhoto(photoBitmap: Bitmap){
        binding.searchImageView.setImageBitmap(photoBitmap)
        binding.startSearchLayout.visibility = View.GONE
        binding.resultsLayout.visibility = View.VISIBLE
    }

    private fun goToHealthResults(lastImage: Bitmap) {
        fetch(lastImage)
    }

    private fun fetch(lastImage: Bitmap) {
        lifecycleScope.launch(Dispatchers.IO) {
            val result = PlantIdHealth().makeHealthAssesmentFromImage(lastImage)
            withContext(Dispatchers.Main) {
                viewModel.healthAssessment = result

                val action = R.id.action_drPlantisFragment_to_drPlantisSearchResultsFragment
                findNavController().navigate(action, bundleOf())
            }
        }
    }
}