package ar.utn.frba.mobile.plantis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.utn.frba.mobile.plantis.*
import ar.utn.frba.mobile.plantis.databinding.FragmentRemindersBinding
import java.time.DayOfWeek
import java.time.LocalDate

class RemindersFragment : Fragment() {
    lateinit var binding: FragmentRemindersBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setTopBarTitle("Reminders")
        (activity as MainActivity).hideNavigationIcon()

        val storagePlants = PlantisStorage.getPlantis(requireActivity()).second.plants

        val allReminders =
            storagePlants
                .flatMap { plant ->
                    plant.reminders.map { reminder ->
                        ReminderAndPlant(reminder, plant.name, plant.imageUrl)
                    }
                }
                .filter { it.reminder.isActive!! }

        val days = getListOfDaysStartingWithToday()
        val reminders = days.flatMap { getDayReminders(it, allReminders) }

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = AllRemindersAdapter(view, reminders)

        recyclerView = binding.myRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun getListOfDaysStartingWithToday(): MutableList<DayOfWeek> {
        val days = mutableListOf<DayOfWeek>()
        val today = LocalDate.now().dayOfWeek
        var j = today.value
        days.add(today)
        for (i in 1..6) {
            val day = DayOfWeek.of(j).plus(1)
            days.add(day)
            if (j == 7) j = 1 else j++
        }
        return days
    }

    // TODO filter only active reminders
    private fun getDayReminders(day: DayOfWeek, allReminders: List<ReminderAndPlant>): List<Any> {
        val dayReminders = mutableListOf<Any>(day)

        val remindersFiltered = allReminders.filter { it.reminder.frequency!!.contains(day) }.sortedBy { it.reminder.hour }
        if (remindersFiltered.isNullOrEmpty())
            dayReminders.add(NoReminders())
        else
            remindersFiltered.forEach { dayReminders.add(it) }
        return dayReminders
    }
}

data class ReminderAndPlant(val reminder: Reminder, val plantName: String?, val plantImageUrl: String?)
class NoReminders