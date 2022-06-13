package ar.utn.frba.mobile.plantis.client.healthAssesment

import android.content.Context
import android.graphics.Bitmap

class PlantIdHealthMock(val context: Context) : HealthAssesmentAPI() {

    override fun identifyIllness(imageBitmap: Bitmap): String {
        val resources = context.resources

        return resources
            .openRawResource(resources.getIdentifier("api_health_assessment_response", "raw", context.packageName))
            .bufferedReader().use { it.readText() }
    }
}