package rconnect.retvens.technologies.OnBoarding.ChainHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rconnect.retvens.technologies.OnBoarding.SingleHotel.FinalOnboardingScreen
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivitySecondOnboardingChainScreenBinding
import rconnect.retvens.technologies.databinding.ActivityThirdOnboardingChainScreenBinding

class ThirdOnboardingChainScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivityThirdOnboardingChainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityThirdOnboardingChainScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)


        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }

        bindingTab.cardChainNext.setOnClickListener {
            startActivity(Intent(this,FinalOnboardingScreen::class.java))
        }

    }
}