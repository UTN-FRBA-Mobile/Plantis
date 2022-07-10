package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import ar.utn.frba.mobile.plantis.PlantisStorage
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentEditPlantNameBinding
import io.github.muddz.styleabletoast.StyleableToast
import java.util.*

class EditPlantNameFragment : Fragment() {
    lateinit var binding: FragmentEditPlantNameBinding
    lateinit var plantName: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEditPlantNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        plantName = arguments?.getSerializable("plantName") as String
        super.onViewCreated(view, savedInstanceState)
        binding.myPlantName.text = plantName
        binding.okEditPlantNameButton.setOnClickListener{ changePlantName(view)}
        binding.cancelEditPlantNameButton.setOnClickListener{ toMyPlantis(view)}
    }

    private fun toMyPlantis(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

    private fun toMyGarden(view: View) {
        val action = R.id.action_editPlantNameFragment_to_myGardenFragment
        val bundle = bundleOf()
        Navigation.findNavController(view).navigate(action, bundle)
    }

    private fun changePlantName(view: View){
        val newPlantName = binding.newPlantName.text.toString()
        if(newPlantName == ""){
            StyleableToast.makeText(requireContext(), "Empty plant name", Toast.LENGTH_LONG, R.style.mytoast).show();
        } else{
            PlantisStorage.changePlantName(requireActivity(), newPlantName, plantName)
            toMyGarden(view)
        }
    }

}