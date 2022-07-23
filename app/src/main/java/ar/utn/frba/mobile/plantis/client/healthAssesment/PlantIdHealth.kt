package ar.utn.frba.mobile.plantis.client.healthAssesment

import android.graphics.Bitmap
import ar.utn.frba.mobile.plantis.client.PlantIdUtils
import org.json.JSONArray
import org.json.JSONObject

class PlantIdHealth : HealthAssesmentAPI() {
    override fun identifyIllness(imageBitmap: Bitmap): String {
        val apiKey = "otJLHGSVrmCZaMP2cBcg72L2r2V8INUFIFen3VUVtDNoHzsWzn"

        val data = JSONObject()
        data.put("api_key", apiKey)

        val images = JSONArray()
        images.put(PlantIdUtils.base64StringFromBitmap(imageBitmap))
        data.put("images", images)

        val modifiers = JSONArray()
            .put("crops_fast")
            .put("similar_images")
        data.put("modifiers", modifiers)

        data.put("language", "en")
        val diseaseDetails = JSONArray()
            .put("cause")
            .put("common_names")
            .put("classification")
            .put("description")
            .put("treatment")
            .put("url")
        data.put("disease_details", diseaseDetails)

        return PlantIdUtils.sendPostRequest("https://api.plant.id/v2/health_assessment", data)
    }
}