package ar.utn.frba.mobile.plantis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GardenAdapter(val view: View, val plantList: List<PlantDetail>) : RecyclerView.Adapter<GardenAdapter.GardenViewHolder>() {

    class GardenViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        val plantItem: LinearLayout = view.findViewById(R.id.plant_item)
        val plantImage: ImageView = view.findViewById(R.id.plant_image)
        var plantTextView: TextView = view.findViewById(R.id.plant_name)
        val plantScientificNameTextView: TextView = view.findViewById(R.id.plant_scientific_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val context = parent.context
        val view : View = LayoutInflater.from(context).inflate(R.layout.view_listitem_plant, parent, false)
        return GardenViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        val plant = plantList[position]
        holder.plantTextView.text = plant.name
        holder.plantScientificNameTextView.text = plant.scientificName
        Glide.with(holder.context).load(plant.imageUrl).into(holder.plantImage)
        holder.plantItem.setOnClickListener {
            val action = R.id.action_myGardenFragment_to_myPlantisFragment
            val bundle = bundleOf("details" to plant, "isMyPlant" to true)
            findNavController(view).navigate(action, bundle)
        }
    }

    override fun getItemCount() = plantList.size
}
