package ar.utn.frba.mobile.plantis

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.fragments.ReminderAndPlant
import com.bumptech.glide.Glide
import com.google.android.material.switchmaterial.SwitchMaterial
import org.w3c.dom.Text
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
class AllRemindersAdapter(val view: View, val remindersList: List<Any>) : RecyclerView.Adapter<AllRemindersAdapter.RemindersViewHolder>() {
    private val DAY = 1
    private val REMINDER = 2
    private val NO_REMINDERS = 3

    class RemindersViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        fun bind(item: Any) = when (item) {
            is ReminderAndPlant -> bindReminder(item)
            is DayOfWeek -> bindDay(item)
            else -> null
        }

        private fun bindReminder(reminder: ReminderAndPlant) {
            val plantName = view.findViewById<TextView>(R.id.plant_name)
            val reminderName = view.findViewById<TextView>(R.id.reminder_name)
            val reminderHour = view.findViewById<TextView>(R.id.reminder_hour)
            val plantImage = view.findViewById<ImageView>(R.id.plant_image)
            plantName.text = reminder.plantName
            reminderName.text = reminder.reminder.name
            reminderHour.text = reminder.reminder.hour
            Glide.with(context).load(reminder.plantImageUrl).into(plantImage)
        }

        private fun bindDay(day: DayOfWeek) {
            val dayTextView = view.findViewById<TextView>(R.id.day)
            dayTextView.text = day.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindersViewHolder {
        val layout = when (viewType) {
            REMINDER -> R.layout.view_reminder
            DAY -> R.layout.view_day
            else -> R.layout.view_no_reminders
        }

        val context = parent.context
        val view: View = LayoutInflater.from(context).inflate(layout, parent, false)
        return RemindersViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RemindersViewHolder, position: Int) {
        holder.bind(remindersList[position])
    }

    override fun getItemCount() = remindersList.size

    override fun getItemViewType(position: Int) =
        when (remindersList[position]) {
            is ReminderAndPlant -> REMINDER
            is DayOfWeek -> DAY
            else -> NO_REMINDERS
        }
}