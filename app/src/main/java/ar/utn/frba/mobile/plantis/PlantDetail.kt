package ar.utn.frba.mobile.plantis

import java.io.Serializable

data class PlantDetail(
    val name: String? = null,
    val scientificName: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val wikiUrl: String? = null,
    val reminders: MutableList<Reminder> = mutableListOf()
): Serializable