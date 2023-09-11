package rconnect.retvens.technologies.PropertyDashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview.GyroscopeObserver
import rconnect.retvens.technologies.PropertyConfiguration.PanoramaViewsActivity
import rconnect.retvens.technologies.databinding.FragmentPropertyDashboardBinding


class PropertyDashboardFragment : Fragment() {

    private lateinit var bindingTab: FragmentPropertyDashboardBinding
    private var gyroscopeObserver: GyroscopeObserver? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        bindingTab = FragmentPropertyDashboardBinding.inflate(layoutInflater,container,false)
        return bindingTab.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        gyroscopeObserver = GyroscopeObserver()
//        // Set the maximum radian the device should rotate to show image's bounds.
//        // It should be set between 0 and π/2.
//        // The default value is π/9.
//        // Set the maximum radian the device should rotate to show image's bounds.
//        // It should be set between 0 and π/2.
//        // The default value is π/9.
//        gyroscopeObserver!!.setMaxRotateRadian(Math.PI / 9)
//
//        // Set GyroscopeObserver for PanoramaImageView.
//        // Set GyroscopeObserver for PanoramaImageView.
//        bindingTab.panoramaImageView.setGyroscopeObserver(gyroscopeObserver)
//
//
//        bindingTab.panoramaImageView.setEnablePanoramaMode(true);
//        bindingTab.panoramaImageView.setEnableScrollbar(true);
//        bindingTab.panoramaImageView.setInvertScrollDirection(false);
//
//        val gestureDetector = GestureDetector(context, MyGestureListener())
//
//      bindingTab.panoramaImageView.setOnPanoramaScrollListener { view, offsetProgress ->
//
//          Log.e("scroll",offsetProgress.toString())
//
//      }

        bindingTab.panoramaViewCard.setOnClickListener {
            startActivity(Intent(requireContext(), PanoramaViewsActivity::class.java))
        }
    }







}

