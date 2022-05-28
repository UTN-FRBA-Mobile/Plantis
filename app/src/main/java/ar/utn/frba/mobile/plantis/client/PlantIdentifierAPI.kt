package ar.utn.frba.mobile.plantis.client

import android.graphics.Bitmap
import com.fasterxml.jackson.databind.ObjectMapper

abstract class PlantIdentifierAPI {
    fun identifyPlantFromImage(imageBitmap: Bitmap): List<Suggestion> {
        val responseAsString = identifyPlant(imageBitmap)
        return getSuggestionsFromResponse(responseAsString)
    }

    abstract fun identifyPlant(imageBitmap: Bitmap): String

    private fun getSuggestionsFromResponse(jsonResponse: String): List<Suggestion> {
        val plantIdentificationResponse = ObjectMapper().readValue(jsonResponse, PlantIdentificationResponse::class.java)
        return plantIdentificationResponse.suggestions!!
    }
}