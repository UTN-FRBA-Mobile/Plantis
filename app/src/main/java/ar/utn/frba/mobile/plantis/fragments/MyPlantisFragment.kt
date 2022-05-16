package ar.utn.frba.mobile.plantis.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ar.utn.frba.mobile.plantis.PlantDetail
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentMyPlantisBinding
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchResultsBinding
import com.bumptech.glide.Glide

class MyPlantisFragment : Fragment() {
    lateinit var binding: FragmentMyPlantisBinding
    lateinit var _context: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyPlantisBinding.inflate(inflater, container, false)
        _context = container!!.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val plantDetails = arguments?.getSerializable("details") as PlantDetail
        binding.myPlantName.text = plantDetails.name
        binding.plantDescription.text = plantDetails.description
        binding.scientificName.text = plantDetails.scientificName
        Glide.with(_context).load(plantDetails.imageUrl).into(binding.plantImage)
    }
}