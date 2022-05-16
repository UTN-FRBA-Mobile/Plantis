package ar.utn.frba.mobile.plantis.client

import com.fasterxml.jackson.annotation.JsonProperty

data class PlantIdentificationResponse(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("suggestions")
    val suggestions: List<Suggestion>?
)

data class Suggestion(
    @JsonProperty("confirmed")
    val confirmed: Boolean?,
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("plant_details")
    val plantDetails: PlantDetails?,
    @JsonProperty("plant_name")
    val plantName: String?,
    @JsonProperty("probability")
    val probability: Double?,
    @JsonProperty("similar_images")
    val similarImages: List<SimilarImage>?
)

data class PlantDetails(
    @JsonProperty("common_names")
    val commonNames: List<String>?,
    @JsonProperty("edible_parts")
    val edibleParts: List<String>?,
    @JsonProperty("propagation_methods")
    val propagationMethods: List<String>?,
    @JsonProperty("scientific_name")
    val scientificName: String?,
    @JsonProperty("structured_name")
    val structuredName: StructuredName?,
    @JsonProperty("synonyms")
    val synonyms: List<String>?,
    @JsonProperty("taxonomy")
    val taxonomy: Taxonomy?,
    @JsonProperty("url")
    val url: String?,
    @JsonProperty("wiki_description")
    val wikiDescription: WikiObject?,
    @JsonProperty("wiki_image")
    val wikiImage: WikiObject?
)

data class SimilarImage(
    @JsonProperty("citation")
    val citation: String?,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("license_name")
    val licenseName: String?,
    @JsonProperty("license_url")
    val licenseUrl: String?,
    @JsonProperty("similarity")
    val similarity: Double?,
    @JsonProperty("url")
    val url: String?,
    @JsonProperty("url_small")
    val urlSmall: String?
)

data class StructuredName(
    @JsonProperty("genus")
    val genus: String?,
    @JsonProperty("species")
    val species: String?
)

data class Taxonomy(
    @JsonProperty("class")
    val clazz: String?,
    @JsonProperty("family")
    val family: String?,
    @JsonProperty("genus")
    val genus: String?,
    @JsonProperty("kingdom")
    val kingdom: String?,
    @JsonProperty("order")
    val order: String?,
    @JsonProperty("phylum")
    val phylum: String?
)

data class WikiObject(
    @JsonProperty("citation")
    val citation: String?,
    @JsonProperty("license_name")
    val licenseName: String?,
    @JsonProperty("license_url")
    val licenseUrl: String?,
    @JsonProperty("value")
    val value: String?
)