package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.MainActivity
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.SearchResultsAdapter
import ar.utn.frba.mobile.plantis.client.Suggestion
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchResultsBinding

class SearchResultsFragment : Fragment() {
    lateinit var binding: FragmentSearchResultsBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setTopBarTitle("Search Plant Results")
        (activity as MainActivity).showNavigationIcon()


        val suggestions = arguments?.getParcelableArray("suggestions")?.toList()?.filterIsInstance<Suggestion>()
        val wantsToAddPlant = arguments?.getBoolean("wantsToAddPlant")
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = SearchResultsAdapter(view, suggestions!!, wantsToAddPlant!!)

        recyclerView = view.findViewById<RecyclerView>(R.id.search_recycler_view).apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}