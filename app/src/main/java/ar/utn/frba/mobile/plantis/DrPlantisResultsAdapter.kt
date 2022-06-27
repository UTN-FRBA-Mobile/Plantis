package ar.utn.frba.mobile.plantis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.client.Suggestion
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment
import com.bumptech.glide.Glide

class DrPlantisResultsAdapter(
    val view: View, private val healthAssessment: HealthAssessment) : RecyclerView.Adapter<DrPlantisResultsAdapter.DrPlantisResultsViewHolder>() {

    class DrPlantisResultsViewHolder(val view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.title)
        val icon: ImageView = view.findViewById(R.id.icon)
        val subtitleText: TextView = view.findViewById(R.id.description)
        val infoButton: ImageView = view.findViewById(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrPlantisResultsViewHolder {
        val context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)
        return DrPlantisResultsViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: DrPlantisResultsViewHolder, position: Int) {
        val disease = healthAssessment.diseases?.get(position)
        val diseaseName = disease?.name
        val context = holder.context
//
//        Glide.with(context).load(suggestion.plantDetails?.wikiImage?.value).into(holder.icon)
        holder.titleText.text = diseaseName
        holder.subtitleText.text = context.getString(R.string.Probabibilidad,"%.2f".format(disease?.probability))

//        holder.infoButton.setOnClickListener {
//            val action = R.id.action_fragment_search_results_to_myPlantisFragment
//            val bundle = bundleOf(
//                "details" to mapSuggestionToPlantDetail(suggestion),
//            )
//            Navigation.findNavController(view).navigate(action, bundle)
//        }

//    private fun mapSuggestionToPlantDetail(suggestion: Suggestion): PlantDetail {
//        val plantDetails = suggestion.plantDetails
//        return PlantDetail(
//            name = plantDetails?.commonNames?.first(),
//            scientificName = plantDetails?.scientificName,
//            description = plantDetails?.wikiDescription?.value,
//            imageUrl = plantDetails?.wikiImage?.value,
//            wikiUrl = plantDetails?.url
//        )
//    }
    }
    override fun getItemCount() = healthAssessment.diseases!!.size

}