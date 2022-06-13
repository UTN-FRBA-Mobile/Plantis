package ar.utn.frba.mobile.plantis.client.healthAssesment

import android.graphics.Bitmap
import com.fasterxml.jackson.databind.ObjectMapper

abstract class HealthAssesmentAPI {
    fun makeHealthAssesmentFromImage(imageBitmap: Bitmap): HealthAssessment {
        val responseAsString = identifyIllness(imageBitmap)
        return getHealthAssessmentsFromResponse(responseAsString)
    }

    abstract fun identifyIllness(imageBitmap: Bitmap): String

    private fun getHealthAssessmentsFromResponse(jsonResponse: String): HealthAssessment {
        val healthAssessmentResponse = ObjectMapper().readValue(jsonResponse, HealthAssessmentResponse::class.java)
        return healthAssessmentResponse.healthAssessment!!
    }
}