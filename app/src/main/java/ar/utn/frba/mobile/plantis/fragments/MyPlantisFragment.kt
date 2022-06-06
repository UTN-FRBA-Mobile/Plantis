package ar.utn.frba.mobile.plantis.fragments

import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentMyPlantisBinding
import com.bumptech.glide.Glide
import java.time.DayOfWeek
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O) // TODO: ver como sacar esto
class MyPlantisFragment : Fragment() {
    lateinit var binding: FragmentMyPlantisBinding
    lateinit var _context: Context
    lateinit var recyclerView: RecyclerView

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

        if (plantDetails.reminders.isEmpty()) {
            binding.remindersLayout.visibility = View.GONE
            return
        }

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = RemindersAdapter(view, plantDetails.reminders)

        recyclerView = view.findViewById<RecyclerView>(R.id.plantis_reminders).apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}