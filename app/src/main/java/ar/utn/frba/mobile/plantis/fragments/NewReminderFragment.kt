package ar.utn.frba.mobile.plantis.fragments


import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentNewReminderBinding
import java.time.DayOfWeek
import java.util.*

class NewReminderFragment : Fragment() {
    lateinit var binding: FragmentNewReminderBinding
    lateinit var plantName: String
    var hour = 0
    var minute = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        plantName = arguments?.getSerializable("plantName") as String
        super.onViewCreated(view, savedInstanceState)
        binding.newReminderTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute))
        binding.newReminderTimeButton.setOnClickListener{goToTimePicker(view)}
        binding.okNewReminderButton.setOnClickListener{ addReminder(view)}
        binding.cancelNewReminderButton.setOnClickListener{ toMyGarden(view)}
    }

    private fun toMyGarden(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addReminder(view: View) {
        val newReminderName = binding.newReminderName.text.toString()
        val days = mapOf(
            DayOfWeek.MONDAY to binding.daypickerMonday,
            DayOfWeek.TUESDAY to binding.daypickerTuesday,
            DayOfWeek.WEDNESDAY to binding.daypickerWednesday,
            DayOfWeek.THURSDAY to binding.daypickerThursday,
            DayOfWeek.FRIDAY to binding.daypickerFriday,
            DayOfWeek.SATURDAY to binding.daypickerSaturdat,
            DayOfWeek.SUNDAY to binding.daypickerSunday,
        )
        val newReminderDays = days.filter{ it.value.isChecked }.keys.toList()
        val newReminderTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
        scheduleNotification()
        PlantisStorage.addReminder(requireActivity(), Reminder(newReminderName, newReminderTime, newReminderDays, true), plantName)
        Navigation.findNavController(view).popBackStack()
    }

    fun goToTimePicker(view: View?) {
        val onTimeSetListener = OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
            hour = selectedHour
            minute = selectedMinute
            binding.newReminderTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute))
        }

        val timePickerDialog = TimePickerDialog(this.context,  onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun scheduleNotification()
    {
        val intent = Intent(context, Notification::class.java)
        val title = "Plantis"
        val message = "It's time to water PLANTNAME!"
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
//        showAlert(time, title, message)
    }
    private fun getTime(): Long
    {
        val minute = 11
        val hour = 11
        val day = 5
        val month = 6 //Starts in 0
        val year = 2022

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }
}


