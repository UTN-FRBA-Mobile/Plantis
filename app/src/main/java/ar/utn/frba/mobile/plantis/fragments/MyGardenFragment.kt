package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.GardenAdapter
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.MyGardenFragmentBinding

class MyGardenFragment : Fragment() {
    lateinit var binding: MyGardenFragmentBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MyGardenFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.my_garden_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaPlantas = listOf<String>("Potus", "Suculenta", "Cactus","Helecho", "Albahaca", "Perejil", "Romero",
        "Menta", "Oregano", "Tomillo", "Salvia", "Eneldo", "Cilantro", "Lavanda", "Aloe vera", "Hibiscus", "Orquidea", "Gardenia",
        "Jazmin", "Rosa", "Cala", "Geranio", "Violeta", "Margarita")

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = GardenAdapter(view,listaPlantas)

        recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}