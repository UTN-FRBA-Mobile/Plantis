package ar.utn.frba.mobile.plantis.client

import android.content.Context
import android.graphics.Bitmap
import ar.utn.frba.mobile.plantis.client.PlantIdUtils.base64StringFromBitmap
import ar.utn.frba.mobile.plantis.client.PlantIdUtils.sendPostRequest
import org.json.JSONArray
import org.json.JSONObject


class PlantId(val context: Context) : PlantIdentifierAPI() {

    override fun identifyPlant(imageBitmap: Bitmap): String {
        val apiKey = "otJLHGSVrmCZaMP2cBcg72L2r2V8INUFIFen3VUVtDNoHzsWzn"

        val data = JSONObject()
        data.put("api_key", apiKey)

        val images = JSONArray()
        images.put(base64StringFromBitmap(imageBitmap))
        data.put("images", images)

        val modifiers = JSONArray()
            .put("similar_images")
        data.put("modifiers", modifiers)

        data.put("plant_language", "en")
        val plantDetails = JSONArray()
            .put("common_names")
            .put("url")
            .put("edible_parts")
            .put("propagation_methods")
            .put("scientific_name")
            .put("structured_name")
            .put("wiki_description")
            .put("wiki_image")
            .put("taxonomy")
            .put("synonyms")
        data.put("plant_details", plantDetails)

    return sendPostRequest("https://api.plant.id/v2/identify", data)
    }
}