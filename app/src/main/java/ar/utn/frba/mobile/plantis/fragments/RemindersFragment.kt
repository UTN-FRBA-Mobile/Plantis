package ar.utn.frba.mobile.plantis.fragments

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import ar.utn.frba.mobile.plantis.R
import ar.utn.frba.mobile.plantis.databinding.FragmentRemindersBinding
import java.util.*


class RemindersFragment : Fragment() {
    private lateinit var binding: FragmentRemindersBinding
    private var listener: notificationListener? = null
    private var _binding: FragmentRemindersBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRemindersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener { onButtonPressed() }
    }

    private fun onButtonPressed() {
        listener?.scheduleNotification()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is notificationListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


}

interface notificationListener {
    fun scheduleNotification()
}