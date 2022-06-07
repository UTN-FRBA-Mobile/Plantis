package ar.utn.frba.mobile.plantis.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentMyPlantisBinding
import com.bumptech.glide.Glide


@RequiresApi(Build.VERSION_CODES.O) // TODO: ver como sacar esto
class MyPlantisFragment : Fragment() {
    lateinit var binding: FragmentMyPlantisBinding
    lateinit var _context: Context
    lateinit var recyclerView: RecyclerView
    private var listener: OnFragmentInteractionListener? = null
    private var _binding: FragmentMyPlantisBinding? = null

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
        var url = plantDetails.wikiUrl

//        val singleToneClass: Global = Global.instance
//        singleToneClass.data = plantDetails.wikiUrl


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

        // Forma nueva con View Bindings
        binding.wikipediaLink.setOnClickListener {
            onButtonPressed(plantDetails.wikiUrl)
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun onButtonPressed(wikipediaLink: String?) {
        listener?.redirect(wikipediaLink)
    }

    interface OnFragmentInteractionListener {
        fun redirect(url: String?)
    }

}