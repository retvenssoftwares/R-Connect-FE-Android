package rconnect.retvens.technologies.PropertyDashboard.manageRooms.rooms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.amenities.AllAmenitiesData
import rconnect.retvens.technologies.R

class RoomsFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var allRoomsAdapter: AllRoomsAdapter
    var list = ArrayList<AllAmenitiesData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rooms, container, false)
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

        allRoomsAdapter = AllRoomsAdapter(list, requireContext())
        recyclerView.adapter = allRoomsAdapter
        allRoomsAdapter.notifyDataSetChanged()
    }
}