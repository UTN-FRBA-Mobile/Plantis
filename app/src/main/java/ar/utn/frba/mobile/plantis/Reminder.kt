package ar.utn.frba.mobile.plantis

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

data class Reminder (
    val name: String,
    val hour: LocalTime,
    val frequency: List<DayOfWeek>
): Serializable {
    fun shouldRunEveryDay() = frequency.size == 7
}