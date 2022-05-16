package ar.utn.frba.mobile.plantis

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ar.utn.frba.mobile.plantis.client.PlantIdentification
import com.bumptech.glide.Glide

class SearchResultsAdapter(val context: Activity, private val suggestions: List<PlantIdentification>)
    : ArrayAdapter<PlantIdentification>(context, R.layout.custom_list, suggestions) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        Glide.with(context).load(suggestions[position].imageURL).into(imageView)
        titleText.text = suggestions[position].plantName
        subtitleText.text = context.getString(R.string.Probabibilidad,"%.2f".format(suggestions[position].probability))

        return rowView
    }
}