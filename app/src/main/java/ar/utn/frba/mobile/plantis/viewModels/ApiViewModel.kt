package ar.utn.frba.mobile.plantis.viewModels

import androidx.lifecycle.ViewModel
import ar.utn.frba.mobile.plantis.client.Suggestion
import ar.utn.frba.mobile.plantis.client.healthAssesment.HealthAssessment

class ApiViewModel : ViewModel(){
    var suggestions: List<Suggestion>? = null
    var healthAssessment: HealthAssessment? = null
}