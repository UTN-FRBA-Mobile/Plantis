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
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.MyGardenFragmentBinding

class MyGardenFragment : Fragment() {
    lateinit var binding: MyGardenFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MyGardenFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.my_garden_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.toMyPlantisDetail).setOnClickListener {
            val action = R.id.action_myGardenFragment_to_myPlantisFragment
            findNavController().navigate(action)
        }
    }
}