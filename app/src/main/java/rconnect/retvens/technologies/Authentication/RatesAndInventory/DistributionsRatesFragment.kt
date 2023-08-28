package rconnect.retvens.technologies.Authentication.RatesAndInventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentDepartureBinding
import rconnect.retvens.technologies.databinding.FragmentDistributionsRatesBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class DistributionsRatesFragment : Fragment() {


    private lateinit var bindingTab:FragmentDistributionsRatesBinding
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    private lateinit var calenderAdapter: CalenderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingTab = FragmentDistributionsRatesBinding.inflate(layoutInflater,container,false)

        return bindingTab.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingTab.calenderRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        calenderAdapter = CalenderAdapter()
        cal.add(Calendar.MONTH,1)
        setUpCalendar()



        bindingTab.calenderRecycler.adapter = calenderAdapter
        calenderAdapter.notifyDataSetChanged()

    }

    private fun setUpCalendar() {
        val calendarList = ArrayList<Calendar>() // Use a list of Calendar objects instead
        val today = Calendar.getInstance(Locale.ENGLISH) // Get today's date
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Set the date of monthCalendar to the current day of the month
        val monthCalendar = cal.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH))

        for (i in 1..maxDaysInMonth) {
            calendarList.add(monthCalendar.clone() as Calendar) // Add the current Calendar object to the list
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calenderAdapter.setData(calendarList) // Pass the list of Calendar objects
    }

}