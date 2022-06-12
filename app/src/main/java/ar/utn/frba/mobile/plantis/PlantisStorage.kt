package ar.utn.frba.mobile.plantis

import android.app.Activity
import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Serializable
import java.lang.Exception

object PlantisStorage {
    private const val mode = Context.MODE_PRIVATE

    fun addPlant(activity: Activity, plant: PlantDetail) {
        val sharedPreferences = activity.getPreferences(mode)
        val plantListAsJson = sharedPreferences.getString("plantis", """
            {
                "plantis": []
            }
        """.trimIndent())
        val plantList = ObjectMapper().readValue(plantListAsJson, PlantisDto::class.java)
        plantList.plantis.add(plant)
        val newPlantJson = ObjectMapper().writeValueAsString(plantList)

        with (sharedPreferences.edit()) {
            putString("plantis", newPlantJson)
            apply()
        }
    }

    fun getPlants(activity: Activity): PlantisDto {
        try {
            val sharedPreferences = activity.getPreferences(mode)
            val plantListAsJson = sharedPreferences.getString("plantis", """
            {
                "plantis": []
            }
        """.trimIndent())
            val plantList = ObjectMapper().readValue(plantListAsJson, PlantisDto::class.java)
            return plantList
        } catch (e: Exception) {
            throw e
        }

    }
}

data class PlantisDto(val plantis: MutableList<PlantDetail> = mutableListOf())