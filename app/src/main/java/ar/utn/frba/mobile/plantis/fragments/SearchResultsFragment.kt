package ar.utn.frba.mobile.plantis.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.SearchResultsAdapter
import ar.utn.frba.mobile.plantis.client.PlantIdMock
import ar.utn.frba.mobile.plantis.client.PlantIdentification
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchBinding
import ar.utn.frba.mobile.plantis.databinding.FragmentSearchResultsBinding
import com.bumptech.glide.Glide
import java.net.URL

class SearchResultsFragment : Fragment() {
    lateinit var binding: FragmentSearchResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        val listView = binding.listView
        val suggestions = PlantIdMock.identifyPlantFromImage("")
        val myListAdapter = SearchResultsAdapter(activity as Activity,suggestions)
        listView.adapter = myListAdapter

        return binding.root
    }
}