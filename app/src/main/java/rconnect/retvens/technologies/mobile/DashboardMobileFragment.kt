package rconnect.retvens.technologies.mobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import rconnect.retvens.technologies.R

class DashboardMobileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val childFragment: Fragment = NewBookingMobileFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_booking_fragment_container, childFragment).commit()

        val childFragment1: Fragment = TodayOverviewMobileFragment()
        val transaction1: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction1.replace(R.id.child_property_fragment_container, childFragment1).commit()

    }

}