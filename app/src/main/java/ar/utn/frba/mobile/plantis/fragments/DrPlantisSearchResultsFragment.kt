package ar.utn.frba.mobile.plantis.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.DrPlantisResultsAdapter
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.R.layout.fragment_dr_plantis_search_results
import ar.utn.frba.mobile.plantis.SearchResultsAdapter
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment
import ar.utn.frba.mobile.plantis.databinding.FragmentDrPlantisSearchResultsBinding
import ar.utn.frba.mobile.plantis.databinding.FragmentMyPlantisBinding

class DrPlantisSearchResultsFragment : Fragment() {
    lateinit var binding: FragmentDrPlantisSearchResultsBinding
    lateinit var _context: Context
    lateinit var recyclerView: RecyclerView
    private val fullCircularProgress: Int = 50

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrPlantisSearchResultsBinding.inflate(inflater, container, false)
        _context = container!!.context
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val healthAssessment = arguments?.getParcelable<HealthAssessment>("healthAssessment")
//        binding.isHealthyProbText.text = _context.getString(R.string.health_probability,"%.2f".format(healthAssessment?.isHealthyProbability))
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = DrPlantisResultsAdapter(view, healthAssessment!!)
        val healthyProbabilitity = healthAssessment?.isHealthyProbability!!

        binding.progressCircular.progress = getHealthLevel(healthyProbabilitity)

        if(healthyProbabilitity >= 0.70){
            binding.healthText.text = "Healthy"
        }
        if(healthyProbabilitity < 0.70){
            binding.healthText.text = "Could be better"
        }
        if(healthyProbabilitity < 0.30){
            binding.healthText.text = "Needs treatment"
        }

        recyclerView = view.findViewById<RecyclerView>(R.id.dr_plantis_result_recycler_view).apply{
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun getHealthLevel(percentage: Double): Int {
        return (fullCircularProgress * percentage).toInt()
    }
    }