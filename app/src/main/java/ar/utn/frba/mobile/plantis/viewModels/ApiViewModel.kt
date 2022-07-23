package ar.utn.frba.mobile.plantis.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.utn.frba.mobile.plantis.client.PlantId
import ar.utn.frba.mobile.plantis.client.Suggestion

class ApiViewModel : ViewModel(){

    var bitmapMutableLiveData: Bitmap? = null
    var suggestions: List<Suggestion>? = null

    fun loadSuggestions() {
        suggestions = PlantId().identifyPlantFromImage(bitmapMutableLiveData!!)
    }
   }