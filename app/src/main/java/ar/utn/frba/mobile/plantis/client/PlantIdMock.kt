package ar.utn.frba.mobile.plantis.client

import android.content.Context
import android.graphics.Bitmap

class PlantIdMock(val context: Context) : PlantIdentifierAPI() {
    override fun identifyPlant(imageBitmap: Bitmap): String {
        val resources = context.resources

        return resources
            .openRawResource(resources.getIdentifier("api_identification_response", "raw", context.packageName))
            .bufferedReader().use { it.readText() }
    }
}