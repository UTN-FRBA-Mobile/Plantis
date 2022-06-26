package ar.utn.frba.mobile.plantis

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

data class Reminder (
    val name: String? = null,
    val hour: String? = null,
    val frequency: List<DayOfWeek>? = null
): Serializable {
    fun shouldRunEveryDay() = frequency?.size == 7
}