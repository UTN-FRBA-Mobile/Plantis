package ar.utn.frba.mobile.plantis.client

import android.annotation.SuppressLint
import android.graphics.Bitmap
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.commons.io.output.ByteArrayOutputStream
import org.json.JSONObject
import java.net.URL
import java.util.*

object PlantIdUtils {
    fun sendPostRequest(urlString: String, data: JSONObject): String {
        val url = URL(urlString)
        val requestBody = data.toString()

        val client = OkHttpClient()
        var result: String? = null
        try {
            val request = Request.Builder()
                .url(url)
                .method("POST",requestBody.toRequestBody())
                .build()
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result!!
    }

    @SuppressLint("NewApi")
    fun base64StringFromBitmap(bitmap: Bitmap): String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val encoder: Base64.Encoder = Base64.getEncoder()
        return encoder.encodeToString(byteArray)
    }
}