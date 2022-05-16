package ar.utn.frba.mobile.plantis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.client.Suggestion
import com.bumptech.glide.Glide

class SearchResultsAdapter(val view: View, private val suggestions: List<Suggestion>) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    class SearchResultsViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.title)
        val icon: ImageView = view.findViewById(R.id.icon)
        val subtitleText: TextView = view.findViewById(R.id.description)
        val infoButton: ImageView = view.findViewById(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val context = parent.context
        val view : View = LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)
        return SearchResultsViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        val suggestion = suggestions[position]
        val plantName = suggestion.plantName
        val context = holder.context

        Glide.with(context).load(suggestion.plantDetails?.wikiImage?.value).into(holder.icon)
        holder.titleText.text = plantName
        holder.subtitleText.text = context.getString(R.string.Probabibilidad,"%.2f".format(suggestion.probability))

        holder.infoButton.setOnClickListener {
            val action = R.id.action_fragment_search_results_to_myPlantisFragment
            val bundle = bundleOf("details" to mapSuggestionToPlantDetail(suggestion))
            findNavController(view).navigate(action, bundle)
        }
    }

    override fun getItemCount() = suggestions.size

    private fun mapSuggestionToPlantDetail(suggestion: Suggestion): PlantDetail {
        val plantDetails = suggestion.plantDetails
        return PlantDetail(
            name = plantDetails?.commonNames?.first(),
            scientificName = plantDetails?.scientificName,
            description = plantDetails?.wikiDescription?.value,
            imageUrl = plantDetails?.wikiImage?.value,
            wikiUrl = plantDetails?.url
        )
    }
}