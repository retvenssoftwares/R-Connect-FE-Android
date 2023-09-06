package rconnect.retvens.technologies.PropertyConfiguration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview.CustomPanoramaImageView
import rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview.GyroscopeObserver
import rconnect.retvens.technologies.Panorma.sources.com.gjiazhe.panoramaimageview.PanoramaImageView
import rconnect.retvens.technologies.databinding.ActivityPanoramaViewsBinding

class PanoramaViewsActivity : AppCompatActivity() , PanoramaImageView.OnPanoramaScrollListener {

    private var gyroscopeObserver: GyroscopeObserver? = null
    private lateinit var bindingTab: ActivityPanoramaViewsBinding
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityPanoramaViewsBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        gyroscopeObserver = GyroscopeObserver()

        val listner = PanoramaImageView(applicationContext)
        listner.setOnPanoramaScrollListener { panoramaImageView, f ->
            Log.e("move","22")
        }




        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        // The default value is π/9.
        gyroscopeObserver!!.setMaxRotateRadian(Math.PI / 9)

        // Set GyroscopeObserver for PanoramaImageView.
        bindingTab.panoramaImageView.setGyroscopeObserver(gyroscopeObserver)

        bindingTab.panoramaImageView.setEnablePanoramaMode(true)
        bindingTab.panoramaImageView.setEnableScrollbar(true)
        bindingTab.panoramaImageView.setInvertScrollDirection(false)

        // Initialize GestureDetector for touch gestures

    }

    override fun onResume() {
        super.onResume()
        // Register GyroscopeObserver.
        gyroscopeObserver!!.register(this)
    }

    override fun onPause() {
        super.onPause()
        // Unregister GyroscopeObserver.
        gyroscopeObserver!!.unregister()
    }

    override fun onScrolled(panoramaImageView: PanoramaImageView?, f: Float) {
        Log.e("scroll","moving")
    }


}
