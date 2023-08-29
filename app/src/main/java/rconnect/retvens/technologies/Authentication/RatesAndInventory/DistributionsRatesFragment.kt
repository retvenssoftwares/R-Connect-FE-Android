package rconnect.retvens.technologies.Authentication.RatesAndInventory

import android.os.Bundle
import android.util.Log
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
    private lateinit var ratesInventoryAdapter: RatesInventoryAdapter
    private  var list:ArrayList<RatesInventoryDataClass> = ArrayList()
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
        setUpCalendar()

        addRatesData()

        bindingTab.backCalender.setOnClickListener {
            Log.d("ButtonClick", "Back button clicked")
            cal.add(Calendar.DAY_OF_MONTH, -1)
            Log.d("CalendarValue", "Cal value after subtracting 1 day: ${sdf.format(cal.time)}")
            setUpCalendar()
            Log.d("CalendarValue", "Cal value after setUpCalendar: ${sdf.format(cal.time)}")
        }

        bindingTab.previousCalender.setOnClickListener {
            cal.add(Calendar.WEEK_OF_MONTH,-1)
            setUpCalendar()
            Log.e("click","2")
            Log.d("CalendarValue", "Cal value after subtracting 1 day: ${sdf.format(cal.time)}")
            setUpCalendar()
            Log.d("CalendarValue", "Cal value after setUpCalendar: ${sdf.format(cal.time)}")
        }

        bindingTab.nextCalender.setOnClickListener {
            cal.add(Calendar.DAY_OF_MONTH,1)
            setUpCalendar()
        }

        bindingTab.forwardCalender.setOnClickListener {
            cal.add(Calendar.WEEK_OF_MONTH,1)
        }


        bindingTab.calenderRecycler.adapter = calenderAdapter
        calenderAdapter.notifyDataSetChanged()

        bindingTab.ratesRecycler.layoutManager = LinearLayoutManager(requireContext())

        ratesInventoryAdapter = RatesInventoryAdapter(requireContext(), list)
        bindingTab.ratesRecycler.adapter = ratesInventoryAdapter
        ratesInventoryAdapter.notifyDataSetChanged()

    }

    private fun addRatesData() {
        // Create dummy RoomDetailsData instances


        for (i in 1..7) {
            val roomDetailsList = ArrayList<RoomDetailsData>()
            val roomDetails = RoomDetailsData(
                roomType = "DELUXE ROOM",
                roomPlan = "Deluxe WITH BREAKFAST (1.0000)",
                day1Inventory = "5",
                day1Price = "7002.00",
                day2Inventory = "2",
                day2Price = "5205.00",
                day3Inventory = "0",
                day3Price = "6000.00",
                day4Inventory = "5",
                day4Price = "6500.00",
                day5Inventory = "2",
                day5Price = "8000.00",
                day6Inventory = "1",
                day6Price = "6500.00",
                day7Inventory = "10",
                day7Price = "4000.00"
            )
            roomDetailsList.add(roomDetails)
            roomDetailsList.add(roomDetails)
            roomDetailsList.add(roomDetails)


// Create dummy RatesInventoryDataClass instance
            val ratesInventoryData = RatesInventoryDataClass(
                otaName = "Agoda",
                otaCode = "52521",
                rateType = "Net",
                currency = "INR",
                roomDetails = roomDetailsList
            )

            list.add(ratesInventoryData)
        }
    }


    private fun setUpCalendar() {
        val calendarList = ArrayList<Calendar>()
        val today = Calendar.getInstance(Locale.ENGLISH)
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val monthCalendar = cal.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH))

        for (i in 1..maxDaysInMonth) {
            calendarList.add(monthCalendar.clone() as Calendar)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calenderAdapter.setData(calendarList)
        Log.d("CalendarValue", "CalenderAdapter data updated with ${calendarList.size} days")

        calenderAdapter.notifyDataSetChanged() // Notify the adapter after all changes
    }


}