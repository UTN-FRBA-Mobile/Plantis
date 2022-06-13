package ar.utn.frba.mobile.plantis

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.fasterxml.jackson.databind.ObjectMapper

object PlantisStorage {
    private const val mode = Context.MODE_PRIVATE
    private const val defaultPlantisPreferences = "{\"plants\":[]}"
    private const val preferencesName = "plantis"

    fun addPlant(activity: Activity, plant: PlantDetail) {
        val (sharedPreferences, plantis) = getPlantis(activity)
        plantis.plants.add(plant)
        writePlantisInStorage(plantis, sharedPreferences)
    }

    fun getPlantis(activity: Activity): Pair<SharedPreferences, Plantis> {
        val sharedPreferences = activity.getPreferences(mode)
        val plantisJson = sharedPreferences.getString(preferencesName, defaultPlantisPreferences)
        val plantis = ObjectMapper().readValue(plantisJson, Plantis::class.java)

        return Pair(sharedPreferences, plantis)
    }

    private fun writePlantisInStorage(plantis: Plantis, sharedPreferences: SharedPreferences) {
        val plantisJson = ObjectMapper().writeValueAsString(plantis)
        with(sharedPreferences.edit()) {
            putString("plantis", plantisJson)
            apply()
        }
    }
}