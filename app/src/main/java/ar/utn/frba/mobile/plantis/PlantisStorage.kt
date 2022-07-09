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

    fun addReminder(activity: Activity, reminder: Reminder, plantName: String){
        val (sharedPreferences, plantis) = getPlantis(activity)
        plantis.plants.find { it.name == plantName }?.reminders?.add(reminder)
        writePlantisInStorage(plantis, sharedPreferences)
    }

    fun getPlantis(activity: Activity): Pair<SharedPreferences, Plantis> {
        val sharedPreferences = activity.getPreferences(mode)
        val plantisJson = sharedPreferences.getString(preferencesName, defaultPlantisPreferences)
        val plantis = ObjectMapper().readValue(plantisJson, Plantis::class.java)

        return Pair(sharedPreferences, plantis)
    }

    fun getReminders(activity: Activity, plantName: String): MutableList<Reminder> {
        val (_, plantis) = getPlantis(activity)

        return plantis.plants.find { it.name == plantName }?.reminders ?: mutableListOf()
    }

    fun changePlantName(activity: Activity, newPlantName: String, plantName: String) {
        val (sharedPreferences, plantis) = getPlantis(activity)
        plantis.plants.find { it.name == plantName }?.changeName(newPlantName)
        writePlantisInStorage(plantis, sharedPreferences)
    }

    private fun writePlantisInStorage(plantis: Plantis, sharedPreferences: SharedPreferences) {
        val plantisJson = ObjectMapper().writeValueAsString(plantis)
        with(sharedPreferences.edit()) {
            putString("plantis", plantisJson)
            apply()
        }
    }

    fun updateReminders(activity: Activity, reminders: MutableList<Reminder>, plantName: String?) {
        val (sharedPreferences, plantis) = getPlantis(activity)
        val storageReminders = plantis.plants.find { it.name == plantName }?.reminders
        storageReminders?.apply {
            clear()
            storageReminders.addAll(reminders)
        }
        writePlantisInStorage(plantis, sharedPreferences)
    }
}