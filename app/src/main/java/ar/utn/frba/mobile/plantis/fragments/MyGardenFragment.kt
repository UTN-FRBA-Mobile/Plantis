package ar.utn.frba.mobile.plantis.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentMyGardenBinding
import java.time.DayOfWeek
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O) // TODO: ver como sacar esto
class MyGardenFragment : Fragment() {
    lateinit var binding: FragmentMyGardenBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyGardenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantList = mutableListOf(
            PlantDetail("Pothos", "Epipremnum aureum",
                """
                Epipremnum aureum is a species in the arum family Araceae, native to Mo'orea in the Society Islands of French Polynesia. The species is a popular houseplant in temperate regions but has also become naturalised in tropical and sub-tropical forests worldwide, including northern South Africa, Australia, Southeast Asia, South Asia, the Pacific Islands and the West Indies, where it has caused severe ecological damage in some cases.
                The plant has a number of common names including golden pothos, Ceylon creeper, hunter's robe, ivy arum, house plant, money plant, silver vine, Solomon Islands ivy, marble queen, and taro vine. It is also called devil's vine or devil's ivy because it is almost impossible to kill and it stays green even when kept in the dark. It is sometimes mistakenly labeled as a Philodendron in plant stores. It is commonly known as a money plant in many parts of the Indian subcontinent. It rarely flowers without artificial hormone supplements; the last known spontaneous flowering in cultivation was reported in 1964.
                The plant has gained the Royal Horticultural Society's Award of Garden Merit.
            """.trimIndent(),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Epipremnum_aureum_31082012.jpg/320px-Epipremnum_aureum_31082012.jpg",
                "https://en.wikipedia.org/wiki/Epipremnum_aureum"),
            PlantDetail("Succulent", "Astroloba tenax",
                "Astroloba tenax is a succulent plant of the genus Astroloba, indigenous to the Western Cape Province, South Africa.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Astroloba_tenax_6.jpg/304px-Astroloba_tenax_6.jpg",
                "https://en.wikipedia.org/wiki/Astroloba_tenax")
        )

        val storagePlants = PlantisStorage.getPlantis(requireActivity()).second.plants
        plantList.addAll(storagePlants)

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