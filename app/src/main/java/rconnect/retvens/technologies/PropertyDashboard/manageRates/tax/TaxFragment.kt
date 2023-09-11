package rconnect.retvens.technologies.PropertyDashboard.manageRates.tax

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rconnect.retvens.technologies.PropertyDashboard.manageRooms.amenities.AllAmenitiesData
import rconnect.retvens.technologies.R


class TaxFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var taxAdapter: TaxAdapter
    var list = ArrayList<AllAmenitiesData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tax, container, false)
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
        list.add(AllAmenitiesData("Available"))
        list.add(AllAmenitiesData("Sold"))

        taxAdapter = TaxAdapter(list, requireContext())
        recyclerView.adapter = taxAdapter
        taxAdapter.notifyDataSetChanged()
    }
}