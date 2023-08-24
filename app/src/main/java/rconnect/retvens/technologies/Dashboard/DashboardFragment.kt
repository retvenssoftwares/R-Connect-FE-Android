package rconnect.retvens.technologies.Dashboard

import android.graphics.Color
import android.graphics.Typeface
import android.hardware.lights.Light
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import rconnect.retvens.technologies.Dashboard.BookingNewsFragment.NewBookingFragment
import rconnect.retvens.technologies.Dashboard.PropertyStatisticsFragment.OverviewFragment
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentDashboardBinding
import rconnect.retvens.technologies.databinding.FragmentNewBookingBinding


class DashboardFragment : Fragment(), OnChartValueSelectedListener {

    private lateinit var bindingTab: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingTab = FragmentDashboardBinding.inflate(inflater,container,false)

        return bindingTab.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(NewBookingFragment())
        replaceDetailsFragment(OverviewFragment())

        lineChart()
        pieChart()
    }

    private fun pieChart() {

        bindingTab.pieChart.setUsePercentValues(true)
        bindingTab.pieChart.description.isEnabled = false
        bindingTab.pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        bindingTab.pieChart.dragDecelerationFrictionCoef = 0.95f


//        bindingTab.pieChart.centerText = generateCenterSpannableText()



        bindingTab.pieChart.setDrawHoleEnabled(true)
        bindingTab.pieChart.setHoleColor(Color.WHITE)
        bindingTab.pieChart.setTransparentCircleColor(Color.WHITE)
        bindingTab.pieChart.setTransparentCircleAlpha(110)

        bindingTab.pieChart.holeRadius = 58f
        bindingTab.pieChart.transparentCircleRadius = 61f

        bindingTab.pieChart.setDrawCenterText(false)

        bindingTab.pieChart.rotationAngle = 0f
// enable rotation of the chart by touch
        bindingTab.pieChart.isRotationEnabled = true
        bindingTab.pieChart.isHighlightPerTapEnabled = true

// chart.setUnit(" €");
// chart.setDrawUnitsInChart(true);

// add a selection listener
        bindingTab.pieChart.setOnChartValueSelectedListener(this)



        bindingTab.pieChart.animateY(1400, Easing.EaseInOutQuad)

        val l: Legend = bindingTab.pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

// entry label styling
        bindingTab.pieChart.setEntryLabelColor(Color.WHITE)

        bindingTab.pieChart.setEntryLabelTextSize(12f)

        setPieData(2,100f)
    }

    private fun generateCenterSpannableText(): CharSequence? {

            val s = SpannableString("MPAndroidChart\ndeveloped by Rahul Bhalerao")
            s.setSpan(RelativeSizeSpan(1.7f), 0, 14, 0)
            s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
            s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
            s.setSpan(RelativeSizeSpan(0.8f), 14, s.length - 15, 0)
            s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
            s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
            return s


    }

    private fun lineChart() {
        val lineChart = bindingTab.lineChart

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

        if (bindingTab.lineChart.data != null && bindingTab.lineChart.data.dataSetCount > 0) {
            set1 = bindingTab.lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            bindingTab.lineChart.data.notifyDataChanged()
            bindingTab.lineChart.notifyDataSetChanged()
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
                bindingTab.lineChart.axisLeft.axisMinimum
            }

            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            bindingTab.lineChart.data = data
        }
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

        bindingTab.pieChart.data = data

        // undo all highlights
        bindingTab.pieChart.highlightValues(null)

        bindingTab.pieChart.invalidate()
    }



    private fun replaceFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_booking,fragment)
            transaction.commit()
        }
    }

    private fun replaceDetailsFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmant_container_property,fragment)
            transaction.commit()
        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }


}