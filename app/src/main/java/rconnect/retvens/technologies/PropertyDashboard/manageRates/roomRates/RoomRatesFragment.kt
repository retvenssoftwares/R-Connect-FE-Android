package rconnect.retvens.technologies.PropertyDashboard.manageRates.roomRates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.amenities.AllAmenitiesData
import rconnect.retvens.technologies.R

class RoomRatesFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var roomRatesAdapter: RoomRatesAdapter
    var list = ArrayList<AllAmenitiesData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        setData()

    }

    private fun setData() {
        list.add(AllAmenitiesData("Available"))
        list.add(AllAmenitiesData("Sold"))

        roomRatesAdapter = RoomRatesAdapter(list, requireContext())
        recyclerView.adapter = roomRatesAdapter
        roomRatesAdapter.notifyDataSetChanged()
    }
}