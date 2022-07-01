package ar.utn.frba.mobile.plantis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.time.DayOfWeek

class RemindersAdapter(val view: View, val remindersList: List<Reminder>) : RecyclerView.Adapter<RemindersAdapter.RemindersViewHolder>() {

    class RemindersViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        val reminder: LinearLayout = view.findViewById(R.id.reminder_item)
        val name: TextView = view.findViewById(R.id.reminder_name)
        val hour: TextView = view.findViewById(R.id.reminder_hour)
        val daily: TextView = view.findViewById(R.id.daily)
        val switch: SwitchMaterial = view.findViewById(R.id.reminder_switch)

        val days = mapOf<DayOfWeek, TextView>(
            DayOfWeek.MONDAY to view.findViewById(R.id.monday),
            DayOfWeek.TUESDAY to view.findViewById(R.id.tuesday),
            DayOfWeek.WEDNESDAY to view.findViewById(R.id.wednesday),
            DayOfWeek.THURSDAY to view.findViewById(R.id.thursday),
            DayOfWeek.FRIDAY to view.findViewById(R.id.friday),
            DayOfWeek.SATURDAY to view.findViewById(R.id.saturday),
            DayOfWeek.SUNDAY to view.findViewById(R.id.sunday),
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindersViewHolder {
        val context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_listitem_reminder, parent, false)
        return RemindersViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RemindersViewHolder, position: Int) {
        val reminder = remindersList[position]
        holder.name.text = reminder.name
        holder.hour.text = reminder.hour
        holder.switch.isChecked = reminder.isActive!!
        changeReminderColor(holder)

        setFrequency(reminder, holder)
        holder.switch.setOnClickListener { changeSwitchState(holder, reminder) }
    }

    private fun setFrequency(reminder: Reminder, holder: RemindersViewHolder) {
        if (reminder.shouldRunEveryDay()) {
            holder.daily.visibility = View.VISIBLE
            holder.days.forEach { it.value.visibility = View.GONE }
            return
        }

        reminder.frequency?.forEach { dayOfWeek ->
            val textView = holder.days[dayOfWeek]
            textView?.setTextColor(ContextCompat.getColor(holder.context, R.color.brown))
        }
    }

    private fun changeSwitchState(holder: RemindersViewHolder, reminder: Reminder) {
        changeReminderColor(holder)
        reminder.isActive = holder.switch.isChecked
    }

    private fun changeReminderColor(holder: RemindersViewHolder) {
        val reminderItem = holder.reminder
        if (holder.switch.isChecked) {
            val enabledColor = ContextCompat.getColor(holder.context, R.color.eggshellWhite)
            reminderItem.setBackgroundColor(enabledColor)
        } else {
            val disabledColor = ContextCompat.getColor(holder.context, R.color.lightGray)
            reminderItem.setBackgroundColor(disabledColor)
        }
    }

    override fun getItemCount() = remindersList.size
}