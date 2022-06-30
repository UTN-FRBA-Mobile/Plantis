package ar.utn.frba.mobile.plantis.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.util.toRange
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentRemindersBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

class RemindersFragment : Fragment() {
    lateinit var binding: FragmentRemindersBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storagePlants = PlantisStorage.getPlantis(requireActivity()).second.plants

        val allReminders = storagePlants.flatMap { plant ->
            plant.reminders.map { reminder ->
                ReminderAndPlant(reminder, plant.name, plant.imageUrl)
            }
        }

        // TODO abstraer esta logica
        val today = LocalDate.now().dayOfWeek
        val days = mutableListOf<DayOfWeek>()
        var j = today.value
        days.add(today)
        for (i in 1..6) {
            val day = DayOfWeek.of(j).plus(1)
            days.add(day)
            if(j==7) j=1 else j++
        }

        // TODO ver que hacemos si no hay reminders un dia
        val reminders = days.map {
            getDayReminders(it, allReminders)
        }

        val viewManager = LinearLayoutManager(this.context)
        //val viewAdapter = AllRemindersAdapter(view, storageReminders)

        /*recyclerView = binding.myRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }*/
    }

    private fun getDayReminders(day: DayOfWeek, allReminders: List<ReminderAndPlant>): List<Any> {
        val dayReminders = mutableListOf<Any>()
        dayReminders.add(day)
        dayReminders.add(
            allReminders
                .filter { it.reminder.frequency!!.contains(day) }
                .sortedBy { it.reminder.hour }
        )
        return dayReminders
    }
}

data class ReminderAndPlant(val reminder: Reminder, val plantName: String?, val plantImageUrl: String?)