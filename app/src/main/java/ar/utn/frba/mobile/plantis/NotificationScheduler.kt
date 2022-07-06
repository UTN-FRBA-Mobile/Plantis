package ar.utn.frba.mobile.plantis

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.DayOfWeek
import java.util.*


enum class Day(val dayOfWeek: Int) {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
}

@RequiresApi(Build.VERSION_CODES.O)
class NotificationScheduler(val context: Context?, val activity: Activity?) {
    val dayMap: Map<DayOfWeek, Int> = mapOf(DayOfWeek.MONDAY to 2,
        DayOfWeek.TUESDAY to 3, DayOfWeek.WEDNESDAY to Calendar.WEDNESDAY,
        DayOfWeek.THURSDAY to 5, DayOfWeek.FRIDAY to 6,
        DayOfWeek.SATURDAY to 7, DayOfWeek.SUNDAY to 1)

    fun scheduleNotification(dayOfWeek: DayOfWeek, hour: Int, minute: Int, title: String, message: String)
    {
        val intent = Intent(context, Notification::class.java)
        val calendar = Calendar.getInstance()
        val cal: Calendar = Calendar.Builder()
            .setDate(2022,6,5)
            .setTimeOfDay(hour,minute,0)
            .build()
        cal[Calendar.DAY_OF_WEEK] = dayMap[dayOfWeek]!!

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            cal.timeInMillis, AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }
}