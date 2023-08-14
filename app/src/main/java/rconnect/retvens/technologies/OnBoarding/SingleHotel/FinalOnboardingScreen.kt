package rconnect.retvens.technologies.OnBoarding.SingleHotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityFinalOnboardingScreenBinding

class FinalOnboardingScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivityFinalOnboardingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityFinalOnboardingScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }
    }
}