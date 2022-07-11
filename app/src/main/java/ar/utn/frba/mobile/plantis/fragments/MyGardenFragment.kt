package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentMyGardenBinding

class MyGardenFragment : Fragment() {
    lateinit var binding: FragmentMyGardenBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyGardenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setTopBarTitle("My Garden")
        (activity as MainActivity).hideNavigationIcon()

        val plantList = PlantisStorage.getPlantis(requireActivity()).second.plants

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = GardenAdapter(view, plantList)

        recyclerView = binding.myRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        binding.addButton.setOnClickListener { goToSearch() }
    }

    private fun goToSearch() {
        val action = R.id.action_myGardenFragment_to_searchFragment
        val bundle = bundleOf("wantsToAddPlant" to true)
        findNavController().navigate(action, bundle)
    }
}