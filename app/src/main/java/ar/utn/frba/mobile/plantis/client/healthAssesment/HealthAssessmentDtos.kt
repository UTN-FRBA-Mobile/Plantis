package ar.utn.frba.mobile.plantis.client.healthAssesment

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class HealthAssessmentResponse(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("health_assessment")
    val healthAssessment: HealthAssessment?
): Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class HealthAssessment(
    @JsonProperty("health_assessment")
    val isHealthyProbability: Double?,
    @JsonProperty("health_assessment")
    val isHealthy: Boolean?,
    @JsonProperty("health_assessment")
    val diseases: List<Disease>?
): Parcelable


@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Disease(
    @JsonProperty("probability")
    val probability: Double?,
    @JsonProperty("name")
    val name: String?,
    //similarImages
    @JsonProperty("disease_details")
    val diseaseDetails: DiseaseDetails?
): Parcelable


@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class DiseaseDetails(
    @JsonProperty("cause")
    val cause: String?,
    @JsonProperty("url")
    val url: String?,
    @JsonProperty("treatment")
    val treatment : Treatment
): Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Treatment(
    @JsonProperty("biological")
    val biological: List<String>?,
    @JsonProperty("prevention")
    val prevention: List<String>?,
    @JsonProperty("chemical")
    val chemical: List<String>?
): Parcelable