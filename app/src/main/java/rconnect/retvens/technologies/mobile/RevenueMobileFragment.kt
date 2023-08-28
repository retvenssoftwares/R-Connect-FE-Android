package rconnect.retvens.technologies.mobile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.FileUtils
import rconnect.retvens.technologies.R


class RevenueMobileFragment : Fragment() {

    lateinit var revenueLineChart : LineChart
    lateinit var monthlyRevenueLineChart : LineChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_revenue_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        revenueLineChart = view.findViewById(R.id.revenueLineChart)

        monthlyRevenueLineChart = view.findViewById(R.id.monthlyRevenueLineChart)

        val xAXES = ArrayList<String>()
        val yAXESsin = ArrayList<Entry>()
        val yAXEScos = ArrayList<Entry>()
        var x = 0.0
        val numDataPoints = 400
        for (i in 0 until numDataPoints) {
            val sinFunction = Math.sin(x).toString().toFloat()
            val cosFunction = Math.cos(x).toString().toFloat()
            x = x + 0.1
            yAXESsin.add(Entry(sinFunction, i.toFloat()))
            yAXEScos.add(Entry(cosFunction, i.toFloat()))
            xAXES.add(i, x.toString())
        }
        val xaxes = arrayOfNulls<String>(xAXES.size)
        for (i in xAXES.indices) {
            xaxes[i] = xAXES[i]
        }

        val lineDataSets = ArrayList<ILineDataSet>()

        val lineDataSet1 = LineDataSet(yAXEScos, "Current weak")
        lineDataSet1.setDrawCircles(false)
        lineDataSet1.color = ContextCompat.getColor(requireContext(), R.color.primary)

        val lineDataSet2 = LineDataSet(yAXESsin, "Past weak")
        lineDataSet2.setDrawCircles(false)
        lineDataSet2.color = ContextCompat.getColor(requireContext(), R.color.secondary)

        lineDataSets.add(lineDataSet1)
        lineDataSets.add(lineDataSet2)



        revenueLineChart.setDrawGridBackground(false)
        revenueLineChart.axisRight.isEnabled = false
        revenueLineChart.xAxis.isEnabled = false
        revenueLineChart.setVisibleXRangeMaximum(6.5f)
        revenueLineChart.animateX(2000)

//        revenueLineChart.setVisibleYRangeMaximum(65f, YAxis.AxisDependency.LEFT)

        revenueLineChart.data = LineData(lineDataSets)
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