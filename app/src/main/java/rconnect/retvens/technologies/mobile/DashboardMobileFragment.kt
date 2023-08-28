package rconnect.retvens.technologies.mobile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import rconnect.retvens.technologies.R

class DashboardMobileFragment : Fragment(), OnChartValueSelectedListener {

    lateinit var avgIncomeLineChart : LineChart
    lateinit var pieChart : PieChart

    lateinit var cardTodayOverview : CardView
    lateinit var cardRevenue : CardView
    lateinit var cardBookingStatics : CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avgIncomeLineChart = view.findViewById(R.id.avgIncomeLineChart)
        pieChart = view.findViewById(R.id.pieChart)

        cardTodayOverview = view.findViewById(R.id.cardTodayOverview)
        cardRevenue = view.findViewById(R.id.cardRevenue)
        cardBookingStatics = view.findViewById(R.id.cardBookingStatics)

        lineChart()
        pieChart()

        val childFragment: Fragment = NewBookingMobileFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_booking_fragment_container, childFragment).commit()

        val childFragment1: Fragment = TodayOverviewMobileFragment()
        val transaction1: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction1.replace(R.id.child_property_fragment_container, childFragment1).commit()

        cardTodayOverview.setOnClickListener {

            cardTodayOverview.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            cardRevenue.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            cardBookingStatics.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

            val childFragment1: Fragment = TodayOverviewMobileFragment()
            val transaction1: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction1.replace(R.id.child_property_fragment_container, childFragment1).commit()

        }
        cardRevenue.setOnClickListener {

            cardTodayOverview.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            cardRevenue.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))
            cardBookingStatics.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

            val childFragment1: Fragment = RevenueMobileFragment()
            val transaction1: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction1.replace(R.id.child_property_fragment_container, childFragment1).commit()

        }
        cardBookingStatics.setOnClickListener {

            cardTodayOverview.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            cardRevenue.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            cardBookingStatics.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary))

            val childFragment1: Fragment = BookingMobileFragment()
            val transaction1: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction1.replace(R.id.child_property_fragment_container, childFragment1).commit()

        }
    }

    private fun lineChart() {
        val lineChart = avgIncomeLineChart

        lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        lineChart.setBackgroundColor(Color.WHITE)

        // Disable description text
        lineChart.description.isEnabled = false

        // Enable touch gestures
        lineChart.setTouchEnabled(true)

        // Enable scaling and dragging
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)

        // Enable pinch zooming
        lineChart.setPinchZoom(true)

        lineChart.setDrawGridBackground(false)
        lineChart.maxHighlightDistance = 300f

        val xAxis = lineChart.xAxis
        xAxis.isEnabled = false

        val yAxis = lineChart.axisLeft
        yAxis.setLabelCount(6, false)
        yAxis.setTextColor(Color.WHITE)
        yAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yAxis.setDrawGridLines(false)
        yAxis.axisLineColor = Color.WHITE

        lineChart.axisRight.isEnabled = false

        lineChart.legend.isEnabled = false

        lineChart.animateXY(2000, 2000)

        // Don't forget to refresh the drawing
        lineChart.invalidate()

        // Set up chart data
        setData(10, 50f)
    }
    private fun setData(count: Int, range: Float) {
        val values = ArrayList<Entry>()

        for (i in 0 until count) {
            val num = (Math.random() * (range + 1) + 20).toFloat()
            values.add(Entry(i.toFloat(), num))
        }

        val set1: LineDataSet

        if (avgIncomeLineChart.data != null && avgIncomeLineChart.data.dataSetCount > 0) {
            set1 = avgIncomeLineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            avgIncomeLineChart.data.notifyDataChanged()
            avgIncomeLineChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")

            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.circleHoleColor = Color.WHITE
            val gradientDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.color_chart)
            set1.fillDrawable = gradientDrawable
            set1.color = Color.parseColor("#ACD037")
            set1.fillAlpha = 100
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter = IFillFormatter { dataSet, dataProvider ->
                avgIncomeLineChart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            avgIncomeLineChart.data = data
        }
    }
    private fun pieChart() {

        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        pieChart.dragDecelerationFrictionCoef = 0.95f


//        bindingTab.pieChart.centerText = generateCenterSpannableText()



        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f

        pieChart.setDrawCenterText(false)

        pieChart.rotationAngle = 0f
// enable rotation of the chart by touch
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true
        pieChart.isHapticFeedbackEnabled = true

        pieChart.setDrawCenterText(true)
        pieChart.setDrawEntryLabels(true)
        pieChart.setDrawMarkers(false)

// chart.setUnit(" €");
// chart.setDrawUnitsInChart(true);

// add a selection listener
        pieChart.setOnChartValueSelectedListener(this)



        pieChart.animateY(1400, Easing.EaseInOutQuad)

        val l: Legend = pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

// entry label styling
        pieChart.setEntryLabelColor(Color.GREEN)

        pieChart.setEntryLabelTextSize(10f)
        pieChart.setDrawEntryLabels(false)

        setPieData(2,100f)
    }
    private fun setPieData(count: Int, range: Float) {
        val entries: ArrayList<PieEntry> = ArrayList()

        val parties = arrayOf(
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
        )


        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    parties[i % parties.size],
                    resources.getDrawable(R.drawable.png_ott)
                )
            )
        }

        val emptyValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "" // Return an empty string to remove the label
            }
        }

        val dataSet = PieDataSet(entries, null)

        dataSet.setDrawIcons(false)
        dataSet.setDrawValues(false)
        dataSet.valueFormatter = emptyValueFormatter

//        dataSet.sliceSpace = 3f
//        dataSet.iconsOffset = MPPointF(0f, 40f)
//        dataSet.selectionShift = 5f

        // add a lot of colors


        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#667483"))
        colors.add(Color.parseColor("#272A3D"))

//
//        for (c in ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c)
//
//        for (c in ColorTemplate.JOYFUL_COLORS)
//            colors.add(c)
//
//        for (c in ColorTemplate.COLORFUL_COLORS)
//            colors.add(c)
//
//        for (c in ColorTemplate.LIBERTY_COLORS)
//            colors.add(c)
//
//        for (c in ColorTemplate.PASTEL_COLORS)
//            colors.add(c)

//        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.selectionShift = 0f;

        val data = PieData(dataSet)
//        data.setValueFormatter(PercentFormatter())
//        data.setValueTextSize(11f)
//        data.setValueTextColor(Color.WHITE)

        pieChart.data = data

        // undo all highlights
        pieChart.highlightValues(null)

        pieChart.invalidate()
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }

}