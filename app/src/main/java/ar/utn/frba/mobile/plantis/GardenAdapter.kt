package ar.utn.frba.mobile.plantis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GardenAdapter(val listaPlantas: List<String>) : RecyclerView.Adapter<GardenAdapter.GardenViewHolder> (){

    class GardenViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.view_listitem_plant, parent, false)
        return GardenViewHolder(view)
    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.plant_name).text = listaPlantas[position]
    }

    override fun getItemCount() = listaPlantas.size
}
