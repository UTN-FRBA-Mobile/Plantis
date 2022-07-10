package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import ar.utn.frba.mobile.plantis.PlantDetail
import ar.utn.frba.mobile.plantis.PlantisStorage
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.Reminder
import ar.utn.frba.mobile.plantis.databinding.FragmentNewPlantBinding
import io.github.muddz.styleabletoast.StyleableToast
import java.time.DayOfWeek
import java.util.*

class NewPlantFragment : Fragment() {
    lateinit var binding: FragmentNewPlantBinding
    lateinit var plantDetails: PlantDetail

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        plantDetails = arguments?.getSerializable("plantDetails") as PlantDetail
        super.onViewCreated(view, savedInstanceState)
        binding.customePlantName.setText(plantDetails.name)
        binding.okNewPlantButton.setOnClickListener{ addPlantis(view, plantDetails)}
        binding.cancelNewPlantButton.setOnClickListener{ toMyGarden(view)}
    }

    private fun addPlantis(view: View, plantDetail: PlantDetail) {
        val customePlantName = binding.customePlantName.text.toString()
        if(customePlantName == ""){
            StyleableToast.makeText(requireContext(), "Empty plant name", Toast.LENGTH_LONG, R.style.mytoast).show();
        } else{
            PlantisStorage.addPlant(requireActivity(), plantDetails)
            PlantisStorage.changePlantName(requireActivity(),customePlantName, plantDetails.name!!)
            toMyGarden(view)
        }
    }

    private fun toMyGarden(view: View) {
        val action = R.id.action_newPlantFragment_to_myGardenFragment
        val bundle = bundleOf()
        Navigation.findNavController(view).navigate(action, bundle)
    }

}