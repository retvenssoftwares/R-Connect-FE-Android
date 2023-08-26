package rconnect.retvens.technologies.Dashboard.PropertyStatisticsFragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.FileUtils
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.FragmentBookingStatisticsBinding


class BookingStatisticsFragment : Fragment() {


    private lateinit var bindingTab:FragmentBookingStatisticsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingTab = FragmentBookingStatisticsBinding.inflate(layoutInflater,container,false)

        return bindingTab.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // For First Graph
        bindingTab.lineChart1.description.isEnabled = false

        bindingTab.lineChart1.setDrawGridBackground(false)

        bindingTab.lineChart1.data = generateLineData()
        bindingTab.lineChart1.animateX(3000)

        val tf = ResourcesCompat.getFont(requireContext(),R.font.barlow_bold)

        val l = bindingTab.lineChart1.legend
        l.typeface = tf

        val leftAxis = bindingTab.lineChart1.axisLeft
        leftAxis.typeface = tf
        leftAxis.axisMaximum = 1.2f
        leftAxis.axisMinimum = -1.2f

        bindingTab.lineChart1.axisRight.isEnabled = false

        val xAxis = bindingTab.lineChart1.xAxis
        xAxis.isEnabled = false


        //For Second Graph
        bindingTab.lineChart2.description.isEnabled = false

        bindingTab.lineChart2.setDrawGridBackground(false)

        bindingTab.lineChart2.data = generateLineData()
        bindingTab.lineChart2.animateX(3000)

        val tf1 = ResourcesCompat.getFont(requireContext(),R.font.barlow_bold)

        val l1 = bindingTab.lineChart2.legend
        l1.typeface = tf1

        val leftAxis1 = bindingTab.lineChart2.axisLeft
        leftAxis1.typeface = tf1
        leftAxis1.axisMaximum = 1.2f
        leftAxis1.axisMinimum = -1.2f

        bindingTab.lineChart2.axisRight.isEnabled = false

        val xAxis1 = bindingTab.lineChart2.xAxis
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