package ar.utn.frba.mobile.plantis

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.DayOfWeek

object PlantisStorage {
    private const val mode = Context.MODE_PRIVATE
    private const val defaultPlantisPreferences = "{\"plants\":[]}"
    private const val preferencesName = "plantis"

    fun addPlant(activity: Activity, plant: PlantDetail) {
        val (sharedPreferences, plantis) = getPlantis(activity)
        plantis.plants.add(plant)
        writePlantisInStorage(plantis, sharedPreferences)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addReminder(activity: Activity, reminder: Reminder, newReminderDays: List<DayOfWeek>, plantName: String): Reminder {
        val (sharedPreferences, plantis) = getPlantis(activity)
        reminder.frequency = getFrequency(plantis.plants, newReminderDays)

        plantis.plants.find { it.name == plantName }?.reminders?.add(reminder)
        writePlantisInStorage(plantis, sharedPreferences)
        return reminder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFrequency(plants: MutableList<PlantDetail>, newReminderDays: List<DayOfWeek>): Map<DayOfWeek, Int> {
        val ids = plants.flatMap { it.reminders }.flatMap { it.frequency.values }
        val lastId = ids.maxOrNull() ?: 0

        val frequency = mutableMapOf<DayOfWeek, Int>()
        var nextId = lastId + 1
        newReminderDays.forEach {
            frequency[it] = nextId
            nextId+=1
        }
        return frequency
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