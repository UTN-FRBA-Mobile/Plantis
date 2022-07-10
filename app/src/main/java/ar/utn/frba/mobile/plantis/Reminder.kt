package ar.utn.frba.mobile.plantis

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

data class Reminder (
    val name: String? = null,
    val hour: String? = null,
    var frequency: Map<DayOfWeek, Int> = mapOf(),
    var isActive: Boolean? = null
): Serializable {
    fun shouldRunEveryDay() = frequency.size == 7
}