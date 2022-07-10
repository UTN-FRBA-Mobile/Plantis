package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.utn.frba.mobile.plantis.client.healthAssesment.DiseaseDetails
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment
import ar.utn.frba.mobile.plantis.databinding.FragmentPlantDiseaseBinding

class PlantDiseaseFragment : Fragment() {

    lateinit var binding: FragmentPlantDiseaseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlantDiseaseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val diseaseDetails = arguments?.getParcelable<DiseaseDetails>("diseaseDetails")

        binding.mainText.text = diseaseDetails?.treatment?.biological?.first()
    }
}