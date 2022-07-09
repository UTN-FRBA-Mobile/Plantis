package ar.utn.frba.mobile.plantis.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toEpochMilli() =
    this.atZone(ZoneId.of("America/Argentina/Buenos_Aires")).toInstant().toEpochMilli()

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getNext(dayOfWeek: DayOfWeek) =
    if (this.dayOfWeek == dayOfWeek)
        this
    else
        this.with(TemporalAdjusters.next(dayOfWeek))
