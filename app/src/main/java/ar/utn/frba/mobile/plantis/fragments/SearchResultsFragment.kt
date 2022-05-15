package ar.utn.frba.mobile.plantis.fragments

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
    ): View? {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        val listView = binding.listView
        val suggestions = PlantIdMock.identifyPlantFromImage("")
        val myListAdapter = MyListAdapter(activity as Activity,suggestions)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
        }

        return binding.root
    }
}

class MyListAdapter(private val context: Activity, private val suggestions: List<PlantIdentification>)
        : ArrayAdapter<PlantIdentification>(context, R.layout.custom_list, suggestions) {

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.custom_list, null, true)

            val titleText = rowView.findViewById(R.id.title) as TextView
            val imageView = rowView.findViewById(R.id.icon) as ImageView
            val subtitleText = rowView.findViewById(R.id.description) as TextView

            titleText.text = suggestions[position].plantName
            Glide.with(context).load(suggestions[position].imageURL).into(imageView);
            subtitleText.text = suggestions[position].probability.toString()

            return rowView
        }
    }