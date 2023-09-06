package rconnect.retvens.technologies.mobile.sidenav

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.mobile.hotelDashboard.HotelActivityMobile
import rconnect.retvens.technologies.mobile.sidenav.custom.DayAxisValueFormatter
import rconnect.retvens.technologies.mobile.sidenav.custom.MyAxisValueFormatter
import rconnect.retvens.technologies.mobile.sidenav.custom.XYMarkerView


class EditConfigMobileFragment : Fragment(), OnChartValueSelectedListener, OnMapReadyCallback{

    lateinit var barChart : BarChart
    lateinit var seeAllHotels : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_config_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeAllHotels = view.findViewById(R.id.seeAll)
        barChart = view.findViewById(R.id.barChart)

        barChart.setOnChartValueSelectedListener(this);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(true);

        barChart.setDrawGridBackground(false);

        // chart.setDrawYLabels(false);
        val xAxisFormatter: DayAxisValueFormatter = DayAxisValueFormatter(barChart)

        val xAxis: XAxis = barChart.getXAxis()
        xAxis.position = XAxisPosition.BOTTOM
//        xAxis.typeface = tfLight
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day

        xAxis.labelCount = 7
        xAxis.setValueFormatter(xAxisFormatter)

        val custom: ValueFormatter = MyAxisValueFormatter()

        val leftAxis: YAxis = barChart.getAxisLeft()
//       leftAxis.typeface = tfLight
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis: YAxis = barChart.getAxisRight()
        rightAxis.setDrawGridLines(false)
//        rightAxis.typeface = tfLight
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = custom
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        rightAxis.isEnabled = false

        val l: Legend = barChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f

        val mv = XYMarkerView(requireContext(), xAxisFormatter)
        mv.setChartView(barChart) // For bounds control

        barChart.setMarker(mv) // Set the marker to the chart

        setData(5,6f)

        seeAllHotels.setOnClickListener {
            showHotelInfoDialog()
        }
    }

    private fun showHotelInfoDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialoge_hotel_detail_mobile)

        val mapView = dialog.findViewById<MapView>(R.id.map)
//        supportFM.getMapAsync(this)

        val cardEditProperty = dialog.findViewById<CardView>(R.id.cardEditProperty)
        cardEditProperty.setOnClickListener {
            startActivity(Intent(requireContext(), HotelActivityMobile::class.java))
        }
        with(mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync{
                MapsInitializer.initialize(requireContext())
                setMapLocation(it)
            }
        }
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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

    private fun setData(count: Int, range: Float) {
        val start = 1f
        val values = ArrayList<BarEntry>()
        var i = start.toInt()
        while (i < start + count) {
            val `val` = (Math.random() * (range + 1)).toFloat()
            if (Math.random() * 100 < 25) {
                values.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(rconnect.retvens.technologies.R.drawable.svg_property_statistics)))
            } else {
                values.add(BarEntry(i.toFloat(), `val`))
            }
            i++
        }
        val set1: BarDataSet
        if (barChart.getData() != null &&
            barChart.getData().getDataSetCount() > 0
        ) {
            set1 = barChart.getData().getDataSetByIndex(0) as BarDataSet
            set1.values = values
            barChart.getData().notifyDataChanged()
            barChart.notifyDataSetChanged()
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
//            val gradientFills: MutableList<Fill> = ArrayList<Fill>()
//            gradientFills.add(Fill(startColor1, endColor1))
//            gradientFills.add(Fill(startColor2, endColor2))
//            gradientFills.add(Fill(startColor3, endColor3))
//            gradientFills.add(Fill(startColor4, endColor4))
//            gradientFills.add(Fill(startColor5, endColor5))
//            set1.setFills(gradientFills)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
//            data.setValueTypeface(tfLight)
            data.barWidth = 0.9f
            barChart.setData(data)
        }
    }

/*
    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.viewGithub -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data =
                    Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/BarChartActivity.java")
                startActivity(i)
            }

            R.id.actionToggleValues -> {
                for (set: IDataSet<*> in chart.getData()
                    .getDataSets()) set.setDrawValues(!set.isDrawValuesEnabled)
                barChart.invalidate()
            }

            R.id.actionToggleIcons -> {
                for (set: IDataSet<*> in barChart.getData()
                    .getDataSets()) set.setDrawIcons(!set.isDrawIconsEnabled)
                barChart.invalidate()
            }

            R.id.actionToggleHighlight -> {
                if (barChart.getData() != null) {
                    barChart.getData().setHighlightEnabled(!barChart.getData().isHighlightEnabled())
                    barChart.invalidate()
                }
            }

            R.id.actionTogglePinch -> {
                if (barChart.isPinchZoomEnabled()) barChart.setPinchZoom(false) else barChart.setPinchZoom(
                    true
                )
                barChart.invalidate()
            }

            R.id.actionToggleAutoScaleMinMax -> {
                barChart.setAutoScaleMinMaxEnabled(!barChart.isAutoScaleMinMaxEnabled())
                barChart.notifyDataSetChanged()
            }

            R.id.actionToggleBarBorders -> {
                for (set: IBarDataSet in barChart.getData()
                    .getDataSets()) (set as BarDataSet).barBorderWidth =
                    if (set.getBarBorderWidth() == 1f) 0f else 1f
                barChart.invalidate()
            }

            R.id.animateX -> {
                barChart.animateX(2000)
            }

            R.id.animateY -> {
                barChart.animateY(2000)
            }

            R.id.animateXY -> {
                barChart.animateXY(2000, 2000)
            }

            R.id.actionSave -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
//                    saveToGallery()
                } else {
//                    requestStoragePermission(chart)
                }
            }
        }
        return true
    }
*/

    fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        tvX.setText(String.valueOf(seekBarX.getProgress()))
//        tvY.setText(String.valueOf(seekBarY.getProgress()))
//        setData(seekBarX.getProgress(), seekBarY.getProgress())
        barChart.invalidate()
    }

//    protected fun saveToGallery() {
//        saveToGallery(barChart, "BarChartActivity")
//    }

    fun onStartTrackingTouch(seekBar: SeekBar?) {}

    fun onStopTrackingTouch(seekBar: SeekBar?) {}

    private val onValueSelectedRectF = RectF()

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return
        val bounds = onValueSelectedRectF
        barChart.getBarBounds(e as BarEntry?, bounds)
        val position: MPPointF = barChart.getPosition(e, AxisDependency.LEFT)
        Log.i("bounds", bounds.toString())
        Log.i("position", position.toString())
        Log.i(
            "x-index",
            ("low: " + barChart.getLowestVisibleX()).toString() + ", high: "
                    + barChart.getHighestVisibleX()
        )
        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {}
    override fun onMapReady(p0: GoogleMap?) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        val myPos = LatLng(26.496633, 80.288982)
        p0?.moveCamera(CameraUpdateFactory.newLatLng(myPos))
    }

}