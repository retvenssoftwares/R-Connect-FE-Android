package rconnect.retvens.technologies.mobile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.FileUtils
import rconnect.retvens.technologies.R

class BookingMobileFragment : Fragment() {

    lateinit var revenueLineChart : LineChart
    lateinit var monthlyRevenueLineChart : LineChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        revenueLineChart = view.findViewById(R.id.revenueLineChart)

        monthlyRevenueLineChart = view.findViewById(R.id.monthlyRevenueLineChart)

        revenueLineChart.description.isEnabled = false

        revenueLineChart.setDrawGridBackground(false)

        revenueLineChart.data = generateLineData()
        revenueLineChart.animateX(2000)

        val tf = ResourcesCompat.getFont(requireContext(),R.font.barlow_bold)

        val l = revenueLineChart.legend
        l.typeface = tf

        val leftAxis = revenueLineChart.axisLeft
        leftAxis.typeface = tf
        leftAxis.axisMaximum = 1.2f
        leftAxis.axisMinimum = -1.2f

        revenueLineChart.axisRight.isEnabled = false

        val xAxis = revenueLineChart.xAxis
        xAxis.isEnabled = false



        monthlyRevenueLineChart.description.isEnabled = false

        monthlyRevenueLineChart.setDrawGridBackground(false)

        monthlyRevenueLineChart.data = generateLineData()
        monthlyRevenueLineChart.animateX(2000)

        val tf1 = ResourcesCompat.getFont(requireContext(),R.font.barlow_bold)

        val l1 = monthlyRevenueLineChart.legend
        l1.typeface = tf1

        val leftAxis1 = monthlyRevenueLineChart.axisLeft
        leftAxis1.typeface = tf1
        leftAxis1.axisMaximum = 1.2f
        leftAxis1.axisMinimum = -1.2f

        monthlyRevenueLineChart.axisRight.isEnabled = false

        val xAxis1 = monthlyRevenueLineChart.xAxis
        xAxis1.isEnabled = false

    }


    private fun generateLineData(): LineData? {

        val sets: ArrayList<ILineDataSet> = ArrayList()

        val ds1 = LineDataSet(FileUtils.loadEntriesFromAssets(requireContext().assets, "sine.txt"), "Sine function")
        val ds2 = LineDataSet(FileUtils.loadEntriesFromAssets(requireContext().assets, "cosine.txt"), "Cosine function")

        ds1.lineWidth = 2f
        ds2.lineWidth = 2f

        ds1.setDrawCircles(false)
        ds2.setDrawCircles(false)

        ds1.color = Color.parseColor("#ACD037")
        ds2.color = Color.parseColor("#272A3D")

        // load DataSets from files in assets folder
        sets.add(ds1)
        sets.add(ds2)

        val d = LineData(sets)

        return d
    }

}