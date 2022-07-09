package ar.utn.frba.mobile.plantis.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toEpochMilli() =
    this.atZone(ZoneId.of("America/Argentina/Buenos_Aires")).toInstant().toEpochMilli()
