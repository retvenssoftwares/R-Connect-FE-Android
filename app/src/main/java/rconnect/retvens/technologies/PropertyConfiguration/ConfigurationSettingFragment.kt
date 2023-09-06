package rconnect.retvens.technologies.PropertyConfiguration

import android.app.Dialog
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import rconnect.retvens.technologies.PropertyDashboard.PropertyDashBoardActivity
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentConfigurationSettingBinding


class ConfigurationSettingFragment : Fragment(), OnChartValueSelectedListener,
    ConfigurationHotelsSettingAdapter.OnItemClickListener {


    private lateinit var bindingTab:FragmentConfigurationSettingBinding
    private lateinit var configurationHotelsSettingAdapter: ConfigurationHotelsSettingAdapter
    private lateinit var configurationPlacesSettingAdapter: ConfigurationPlacesSettingAdapter
    private  var list:ArrayList<HotelDataClass> = ArrayList()
    private var listCountry:ArrayList<CountryDataClass> = ArrayList()
    private var latitude:Double = 0.0
    var longitude:Double = 0.0
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView:MapView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingTab = FragmentConfigurationSettingBinding.inflate(layoutInflater,container,false)
        return bindingTab.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bindingTab.ownerHotelRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        bindingTab.placesRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        addChart()
        addData()
        addCountry()

        configurationHotelsSettingAdapter = ConfigurationHotelsSettingAdapter(requireContext(),list)
        bindingTab.ownerHotelRecycler.adapter = configurationHotelsSettingAdapter
        configurationHotelsSettingAdapter.notifyDataSetChanged()

        configurationHotelsSettingAdapter.setOnItemClickListener(this)

        configurationPlacesSettingAdapter = ConfigurationPlacesSettingAdapter(requireContext(),listCountry)
        bindingTab.placesRecycler.adapter = configurationPlacesSettingAdapter
        configurationPlacesSettingAdapter.notifyDataSetChanged()



    }

    private fun addChart() {

        bindingTab.barchart.setOnChartValueSelectedListener(this)

        bindingTab.barchart.setDrawBarShadow(false)
        bindingTab.barchart.setDrawValueAboveBar(true)

        bindingTab.barchart.description.isEnabled = false

// if more than 60 entries are displayed in the chart, no values will be drawn
        bindingTab.barchart.maxVisibleCount

// scaling can now only be done on x- and y-axis separately
        bindingTab.barchart.setPinchZoom(false)

        bindingTab.barchart.drawableState
// chart.setDrawYLabels(false);

        val xAxisFormatter: IAxisValueFormatter = DayAxisValueFormatter( bindingTab.barchart)

        val xAxis =  bindingTab.barchart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = 7


        val custom: IAxisValueFormatter = MyAxisValueFormatter()

        val leftAxis =  bindingTab.barchart.axisLeft
        leftAxis.labelCount = 8
        leftAxis.setDrawLabels(false)
//        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis =  bindingTab.barchart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.labelCount = 8
        rightAxis.setDrawLabels(false)
//        rightAxis.valueFormatter = custom
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val l =  bindingTab.barchart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f

        val mv = XYMarkerView(requireContext(), xAxisFormatter)
        mv.chartView =  bindingTab.barchart // For bounds control
        bindingTab.barchart.marker = mv

        setData(7,60f)
    }

    private fun setData(count: Int, range: Float) {
        val start = 1f
        val values = ArrayList<BarEntry>()

        for (i in start.toInt() until start.toInt() + count) {
            val num = (Math.random() * (range + 1)).toFloat()

            if (Math.random() * 100 < 25) {
                values.add(BarEntry(i.toFloat(), num, resources.getDrawable(R.drawable.check)))
            } else {
                values.add(BarEntry(i.toFloat(), num))
            }
        }

        val set1: BarDataSet

        if (bindingTab.barchart.data != null && bindingTab.barchart.data.dataSetCount > 0) {
            set1 = bindingTab.barchart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            bindingTab.barchart.data.notifyDataChanged()
            bindingTab.barchart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "The year 2017")
            set1.setDrawIcons(false)

            val startColor1 = ContextCompat.getColor(requireContext(), android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(requireContext(), android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(requireContext(), android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(requireContext(), android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(requireContext(), android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)

//            val gradientFills = ArrayList<Fill>()
//            gradientFills.add(Fill(startColor1, endColor1))
//            gradientFills.add(Fill(startColor2, endColor2))
//            gradientFills.add(Fill(startColor3, endColor3))
//            gradientFills.add(Fill(startColor4, endColor4))
//            gradientFills.add(Fill(startColor5, endColor5))
//
//            set1.setFills(gradientFills)

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.4f

            bindingTab.barchart.data = data
        }
    }


    private fun addCountry() {

        listCountry.add(
            CountryDataClass(
                countryImage = R.drawable.png_country1,
                countryName =  "France",
                countryPlace = "Paris"
            )
        )

        listCountry.add(
            CountryDataClass(
                countryImage = R.drawable.png_country2,
                countryName =  "France",
                countryPlace = "Paris"
            )
        )

        listCountry.add(
            CountryDataClass(
                countryImage = R.drawable.png_country3,
                countryName =  "France",
                countryPlace = "Paris"
            )
        )
        listCountry.add(
            CountryDataClass(
                countryImage = R.drawable.png_country3,
                countryName =  "France",
                countryPlace = "Paris"
            )
        )

        listCountry.add(
            CountryDataClass(
                countryImage = R.drawable.png_country3,
                countryName =  "France",
                countryPlace = "Paris"
            )
        )


    }

    private fun addData() {

        list.add(
            HotelDataClass(
            hotelImage = R.drawable.png_hotel1,
            hotelName = "Shapura",
            hotelRate = "5",
            hotelReview = "2000",
            hotelStars = 5f,
            hotelVenue = "Mumbai",
            roomPrice = "$7"
        ))

        list.add(HotelDataClass(
            hotelImage = R.drawable.png_hotel2,
            hotelName = "Shapura",
            hotelRate = "5",
            hotelReview = "2000",
            hotelStars = 5f,
            hotelVenue = "Mumbai",
            roomPrice = "$7"
        ))

        list.add(HotelDataClass(
            hotelImage = R.drawable.png_hotel3,
            hotelName = "Shapura",
            hotelRate = "5",
            hotelReview = "2000",
            hotelStars = 5f,
            hotelVenue = "Mumbai",
            roomPrice = "$7"
        ))

        list.add(HotelDataClass(
            hotelImage = R.drawable.png_hotel4,
            hotelName = "Shapura",
            hotelRate = "5",
            hotelReview = "2000",
            hotelStars = 5f,
            hotelVenue = "Mumbai",
            roomPrice = "$7"
        ))

        list.add(HotelDataClass(
            hotelImage = R.drawable.png_hotel5,
            hotelName = "Shapura",
            hotelRate = "5",
            hotelReview = "2000",
            hotelStars = 5f,
            hotelVenue = "Mumbai",
            roomPrice = "$7"
        ))

    }

    private val onValueSelectedRectF = RectF()

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e != null) {
            val bounds = onValueSelectedRectF
            bindingTab.barchart.getBarBounds(e as BarEntry, bounds)
            val position = bindingTab.barchart.getPosition(e, YAxis.AxisDependency.LEFT)

            Log.i("bounds", bounds.toString())
            Log.i("position", position.toString())

            Log.i(
                "x-index",
                "low: " + bindingTab.barchart.lowestVisibleX + ", high: " + bindingTab.barchart.highestVisibleX
            )

            MPPointF.recycleInstance(position)
        }

    }

    override fun onNothingSelected() {

    }

    override fun onItemClick() {
        openDialog()
    }

    private fun openDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialogue_item_hoteldetails)




        latitude = 22.759430
        longitude = 75.886009

        mapView = dialog.findViewById(R.id.map_show)

        val button = dialog.findViewById<MaterialCardView>(R.id.move_button)

        button.setOnClickListener {
            startActivity(Intent(requireContext(),PropertyDashBoardActivity::class.java))
        }


        mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(p0: GoogleMap) {

                googleMap = p0
                val location = LatLng( latitude, longitude)
                Log.e("lat",latitude.toString())
                Log.e("long",longitude.toString())
                googleMap.addMarker(MarkerOptions().position(location).title("location"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f))
            }

        })


        dialog.show()

    }





}