package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.utn.frba.mobile.plantis.R.layout.fragment_dr_plantis_search_results

class DrPlantisSearchResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_dr_plantis_search_results, container, false)
    }
}