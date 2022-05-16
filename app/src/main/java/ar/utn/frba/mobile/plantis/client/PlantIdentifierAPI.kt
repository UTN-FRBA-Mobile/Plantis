package ar.utn.frba.mobile.plantis.client

import com.fasterxml.jackson.databind.ObjectMapper

abstract class PlantIdentifierAPI {
    fun identifyPlantFromImage(filePath: String): List<Suggestion> {
        val responseAsString = identifyPlant(filePath)
        return getSuggestionsFromResponse(responseAsString)
    }

    abstract fun identifyPlant(filePath: String): String

    private fun getSuggestionsFromResponse(jsonResponse: String): List<Suggestion> {
        val plantIdentificationResponse = ObjectMapper().readValue(jsonResponse, PlantIdentificationResponse::class.java)
        return plantIdentificationResponse.suggestions!!
    }
}