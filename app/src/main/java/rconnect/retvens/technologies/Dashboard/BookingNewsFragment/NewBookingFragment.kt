package rconnect.retvens.technologies.Dashboard.BookingNewsFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import rconnect.retvens.technologies.Authentication.DataCollections.BookingDataClass
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentNewBookingBinding

class NewBookingFragment : Fragment() {

    private lateinit var bindingTab:FragmentNewBookingBinding
    private lateinit var newBookingAdapter: NewBookingAdapter
    private  var bookingList:ArrayList<BookingDataClass> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingTab = FragmentNewBookingBinding.inflate(inflater,container,false)

        return bindingTab.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addData()

        bindingTab.newbookingRecycler.layoutManager = LinearLayoutManager(requireContext())

        newBookingAdapter = NewBookingAdapter(requireContext(),bookingList)
        bindingTab.newbookingRecycler.adapter = newBookingAdapter
        newBookingAdapter.notifyDataSetChanged()


    }

    private fun addData() {
        for (i in 1..10) {
            val newItem = BookingDataClass(
                guestProfile = if (i % 2 == 0) R.drawable.png_dummyimage2 else R.drawable.png_dummyimage,
                guestName = if (i % 2 == 0) "Preet Patil" else "Shivam Tiwari",
                guestDetails = if (i % 2 == 0) "1 Guest|2 Night" else "2 Guest|1 Night", // Change the guest details
                voucherNo = "NH123456789", // Change the voucher number
                ratePlan = "Superior Room Continental Plan", // Change the rate plan
                roomType = "Superior Room", // Change the room type
                pricing = if (i % 2 == 0) "Rs. 5,056.0" else "Rs. 11,050.0",
                checkInDay = if (i % 2 == 0) "Mon" else "Mon",
                checkOutDay = if (i % 2 == 0) "Fri" else "Fri",
                checkInDate = if (i % 2 == 0) "22" else "1",
                checkOutDate = if (i % 2 == 0) "24" else "5",
                ottImages = if (i % 2 == 0) R.drawable.png_mmt else R.drawable.png_ott
            )
            bookingList.add(newItem)
        }

    }

}