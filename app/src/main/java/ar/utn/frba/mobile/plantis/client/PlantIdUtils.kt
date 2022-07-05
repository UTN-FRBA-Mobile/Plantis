package ar.utn.frba.mobile.plantis.client

import android.annotation.SuppressLint
import android.graphics.Bitmap
import org.apache.commons.io.IOUtils
import org.apache.commons.io.output.ByteArrayOutputStream
import org.json.JSONObject
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*

object PlantIdUtils {
    @Throws(Exception::class)
    fun sendPostRequest(urlString: String, data: JSONObject): String {
        val url = URL(urlString)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setDoOutput(true)
        con.setDoInput(true)
        con.setRequestMethod("POST")
        con.setRequestProperty("Content-Type", "application/json")
        val os: OutputStream = con.getOutputStream()
        os.write(data.toString().toByteArray())
        os.close()
        val inputStream: InputStream = con.getInputStream()
        val response: String = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name())
        con.disconnect()
        return response
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