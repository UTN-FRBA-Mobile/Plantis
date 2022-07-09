package ar.utn.frba.mobile.plantis

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import ar.utn.frba.mobile.plantis.common.getNext
import ar.utn.frba.mobile.plantis.common.toEpochMilli
import java.time.*

@RequiresApi(Build.VERSION_CODES.O)
class NotificationScheduler(val context: Context?) {
    fun scheduleFirstNotifications(dayOfWeek: DayOfWeek, hour: Int, minute: Int, plantName: String, reminderName: String)
    {
        val today = LocalDate.now()
        val date = today.getNext(dayOfWeek)
        val dateTime = LocalDateTime.of(date, LocalTime.of(hour, minute, 0, 0))

        scheduleNotification(plantName, reminderName, dateTime, dateTime.toEpochMilli())
    }

    fun scheduleNextNotification(dateTime: LocalDateTime, plantName: String, reminderName: String) {
        val nextOccurrence = dateTime.plusDays(7)

        scheduleNotification(plantName, reminderName, nextOccurrence, nextOccurrence.toEpochMilli())
    }

    private fun scheduleNotification(plantName: String, reminderName: String, dateTime: LocalDateTime, dateTimeInMillis: Long) {
        val intent = Intent(context, Notification::class.java)

        intent.apply {
            putExtra(PLANT_NAME, plantName)
            putExtra(REMINDER_NAME, reminderName)
            putExtra(DATE_TIME, dateTime.toString())
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, notificationID,
            intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeInMillis, pendingIntent)
    }
}