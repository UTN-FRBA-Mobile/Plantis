package ar.utn.frba.mobile.plantis

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.time.LocalDateTime

const val notificationID = 1
const val channelID = "channel1"

const val PLANT_NAME = "plantName"
const val REMINDER_NAME = "reminderName"
const val DATE_TIME = "dateTime"

class Notification : BroadcastReceiver()
{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent)
    {
        val (plantName, reminderName, dateTimeAsString) = getExtras(intent)

        notify(context, plantName, reminderName)
        scheduleNextNotification(context, dateTimeAsString, plantName, reminderName)
    }

    private fun getExtras(intent: Intent): Triple<String?, String?, String?> {
        val plantName = intent.getStringExtra(PLANT_NAME)
        val reminderName = intent.getStringExtra(REMINDER_NAME)
        val dateTimeAsString = intent.getStringExtra(DATE_TIME)
        return Triple(plantName, reminderName, dateTimeAsString)
    }

    private fun notify(context: Context, plantName: String?, reminderName: String?) {
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.mipmap.ic_plantis_foreground)
            .setContentTitle(plantName)
            .setContentText(reminderName)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleNextNotification(context: Context, dateTimeAsString: String?, plantName: String?, reminderName: String?) {
        val notificationScheduler = NotificationScheduler(context)
        val dateTime = LocalDateTime.parse(dateTimeAsString)
        notificationScheduler.scheduleNextNotification(dateTime, plantName!!, reminderName!!)
    }
}