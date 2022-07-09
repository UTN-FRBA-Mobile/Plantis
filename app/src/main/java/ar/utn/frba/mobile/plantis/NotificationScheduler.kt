package ar.utn.frba.mobile.plantis

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import ar.utn.frba.mobile.plantis.common.toEpochMilli
import java.time.*
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
class NotificationScheduler(val context: Context?) {
    fun scheduleFirstNotifications(reminder: Reminder, plantName: String) {
        val today = LocalDateTime.now()
        val hour = reminder.hour!!.take(2).toInt()
        val minute = reminder.hour.takeLast(2).toInt()
        val reminderName = reminder.name!!

        reminder.frequency.forEach {
            val date = today.getNext(it.key, hour, minute)
            val dateTime = LocalDateTime.of(date, LocalTime.of(hour, minute, 0, 0))

            scheduleNotification(plantName, reminderName, dateTime, dateTime.toEpochMilli(), it.value)
        }
    }

    fun scheduleNextNotification(dateTime: LocalDateTime, plantName: String, reminderName: String, alarmId: Int) {
        val nextOccurrence = dateTime.plusDays(7)

        scheduleNotification(plantName, reminderName, nextOccurrence, nextOccurrence.toEpochMilli(), alarmId)
    }

    fun cancel(reminder: Reminder, plantName: String) {
        val today = LocalDateTime.now()
        val hour = reminder.hour!!.take(2).toInt()
        val minute = reminder.hour.takeLast(2).toInt()
        val reminderName = reminder.name!!

        reminder.frequency.forEach {
            val date = today.getNext(it.key, hour, minute)
            val dateTime = LocalDateTime.of(date, LocalTime.of(hour, minute, 0, 0))

            val intent = Intent(context, Notification::class.java)

            intent.apply {
                putExtra(PLANT_NAME, plantName)
                putExtra(REMINDER_NAME, reminderName)
                putExtra(DATE_TIME, dateTime.toString())
            }

            val pendingIntent = PendingIntent.getBroadcast(context, it.value, intent, PendingIntent.FLAG_IMMUTABLE)

            val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun scheduleNotification(plantName: String, reminderName: String, dateTime: LocalDateTime, dateTimeInMillis: Long, alarmId: Int) {
        val intent = Intent(context, Notification::class.java)

        intent.apply {
            putExtra(PLANT_NAME, plantName)
            putExtra(REMINDER_NAME, reminderName)
            putExtra(DATE_TIME, dateTime.toString())
            putExtra(ALARM_ID, alarmId.toString())
        }

        val pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, PendingIntent.FLAG_IMMUTABLE)

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTimeInMillis, pendingIntent)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.getNext(dayOfWeek: DayOfWeek, hour: Int, minute: Int): LocalDate {
    val reminderTime = LocalTime.of(hour, minute)
    return if (this.dayOfWeek == dayOfWeek && this.toLocalTime().isBefore(reminderTime) )
        this.toLocalDate()
    else
        this.with(TemporalAdjusters.next(dayOfWeek)).toLocalDate()
}
