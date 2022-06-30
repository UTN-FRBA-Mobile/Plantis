package ar.utn.frba.mobile.plantis

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
class AllRemindersAdapter(val view: View, val remindersList: List<Any>) : RecyclerView.Adapter<AllRemindersAdapter.RemindersViewHolder>() {
    private val DAY = 1
    private val REMINDER = 2

    class RemindersViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        fun bind(item: Any) = if (item is Reminder) bindReminder(item) else bindDay(item as DayOfWeek)

        private fun bindReminder(reminder: Reminder) {

        }

        private fun bindDay(day: DayOfWeek) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindersViewHolder {
        val layout = if (viewType == REMINDER) R.layout.view_reminder else R.layout.view_day
        val context = parent.context
        val view: View = LayoutInflater.from(context).inflate(layout, parent, false)
        return RemindersViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: RemindersViewHolder, position: Int) {
        holder.bind(remindersList[position])
    }

    override fun getItemCount() = remindersList.size

    override fun getItemViewType(position: Int) = if (remindersList[position] is Reminder) REMINDER else DAY
}