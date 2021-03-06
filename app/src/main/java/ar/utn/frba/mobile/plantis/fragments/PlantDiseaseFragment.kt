package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.utn.frba.mobile.plantis.MainActivity
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
        (activity as MainActivity).setTopBarTitle("Plant Disease")
        (activity as MainActivity).showNavigationIcon()
        val diseaseDetails = arguments?.getParcelable<DiseaseDetails>("diseaseDetails")
        val diseaseName = arguments?.getString("diseaseName")

        binding.diseaseName.text = diseaseName
        binding.diseaseDescription.text = diseaseDetails?.description
        if(diseaseDetails?.treatment?.prevention !== null){
            binding.preventionTreatmentBody.text = parseTreatment(diseaseDetails.treatment.prevention)
        }
        if(diseaseDetails?.treatment?.biological !== null){
            binding.biologicalTreatmentBody.text = parseTreatment(diseaseDetails.treatment.biological)
        }
        if(diseaseDetails?.treatment?.chemical !== null){
            binding.chemicalTreatmentBody.text = parseTreatment(diseaseDetails.treatment.chemical)
        }
    }

    private fun parseTreatment(pasos: List<String>?): String {
        return pasos!!.joinToString("\n")
    }
}