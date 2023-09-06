package rconnect.retvens.technologies.mobile.hotelDashboard.hotelSideNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.joshhalvorson.calendar_date_range_picker.calendar.CalendarPicker
import rconnect.retvens.technologies.R


class PropertyDashboardFragmentMobile : Fragment() {

    lateinit var calendarPicker : CalendarPicker
    lateinit var mapView : MapView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_property_dashboard_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarPicker = view.findViewById(R.id.calendarPicker)
        mapView = view.findViewById(R.id.mapView)

        with(mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync{
                MapsInitializer.initialize(requireContext())
                setMapLocation(it)
            }
        }

    }

    private fun setMapLocation(map : GoogleMap) {

        val position = LatLng(26.496633, 80.288982)

        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            addMarker(MarkerOptions().position(position))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            setOnMapClickListener {
                Toast.makeText(requireContext(), "Clicked on map", Toast.LENGTH_SHORT).show()
            }
        }
    }

}