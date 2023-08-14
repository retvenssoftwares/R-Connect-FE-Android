package rconnect.retvens.technologies.OnBoarding.SingleHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivitySecondOnboardingScreenBinding

class SecondOnboardingScreen : AppCompatActivity() {

    private lateinit var bindingTab : ActivitySecondOnboardingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivitySecondOnboardingScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.cardSingleNext.setOnClickListener {
            startActivity(Intent(this,ThirdOnboardingScreen::class.java))
        }

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }

    }
}