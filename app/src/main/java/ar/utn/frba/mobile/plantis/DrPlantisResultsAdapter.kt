package ar.utn.frba.mobile.plantis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.client.Suggestion
import ar.utn.frba.mobile.plantis.client.healthAssesment.Disease
import ar.utn.frba.mobile.plantis.client.healthAssesment.DiseaseDetails
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment
import com.bumptech.glide.Glide

class DrPlantisResultsAdapter(
    val view: View, private val healthAssessment: HealthAssessment) : RecyclerView.Adapter<DrPlantisResultsAdapter.DrPlantisResultsViewHolder>() {

    class DrPlantisResultsViewHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.title)
        val subtitleText: TextView = view.findViewById(R.id.description)
        val treatmentButton: Button = view.findViewById(R.id.treatment_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrPlantisResultsViewHolder {
        val context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.diseases_list, parent, false)
        return DrPlantisResultsViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: DrPlantisResultsViewHolder, position: Int) {
        val disease = healthAssessment.diseases?.get(position)
        val diseaseName = disease?.name
        val context = holder.context

        holder.titleText.text = diseaseName
        holder.subtitleText.text =
            context.getString(R.string.Probabibilidad, "%.2f".format(disease?.probability))
        holder.treatmentButton.setOnClickListener {
            val action = R.id.action_dr_plantis_search_results_to_plant_disease_fragment
            val bundle = bundleOf(
                "diseaseDetails" to disease?.diseaseDetails,
                "diseaseName" to disease?.name
            )
            Navigation.findNavController(view).navigate(action, bundle)
        }
    }
    override fun getItemCount() = healthAssessment.diseases!!.size

}