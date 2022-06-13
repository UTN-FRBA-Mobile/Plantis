package ar.utn.frba.mobile.plantis.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ar.utn.frba.mobile.plantis.CameraHandler
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.client.PlantIdMock
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment
import ar.utn.frba.mobile.plantis.client.healthAssesment.PlantIdHealthMock
import ar.utn.frba.mobile.plantis.databinding.FragmentDrPlantisBinding

class DrPlantisFragment : Fragment() {
    lateinit var binding: FragmentDrPlantisBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrPlantisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cameraHandler = CameraHandler(binding.drplantisSearchImageView, binding.drplantisNextButton, ::registerForActivityResult)

        binding.drplantisSearchButton.setOnClickListener { cameraHandler.launchTakePictureIntent() }
        binding.drplantisNextButton.setOnClickListener {
            val action = R.id.action_fragment_search_to_fragment_search_results
            goToHealthResults(cameraHandler.lastImageBitmap)
        }
    }
    private fun goToHealthResults(lastImage: Bitmap) {
        val healthAssessment = PlantIdHealthMock(this.requireContext()).makeHealthAssesmentFromImage(lastImage)
        println(healthAssessment)
    }
}