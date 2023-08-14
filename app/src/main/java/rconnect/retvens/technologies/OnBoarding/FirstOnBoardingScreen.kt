package rconnect.retvens.technologies.OnBoarding

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import rconnect.retvens.technologies.OnBoarding.ChainHotel.SecondOnboardingChainScreen
import rconnect.retvens.technologies.OnBoarding.SingleHotel.SecondOnboardingScreen
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityFirstOnBoardingScreenBinding

class FirstOnBoardingScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivityFirstOnBoardingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityFirstOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }


        bindingTab.cardSingle.setOnClickListener {

            bindingTab.heading.text = "Tell us about your property"

            bindingTab.singleLayout.visibility = View.VISIBLE
            bindingTab.propertyChainFrameLayout.visibility = View.GONE

            val colorResId = R.color.primary // Replace with your color resource ID
            val color = ContextCompat.getColor(applicationContext, colorResId)
            bindingTab.cardSingle.strokeColor = color
            bindingTab.text1.setTextColor(color)

            val colorResId1 = R.color.subtitle // Replace with your color resource ID
            val color1 = ContextCompat.getColor(applicationContext, colorResId1)

            bindingTab.cardChain.strokeColor = color1
            bindingTab.text2.setTextColor(color1)
        }

        bindingTab.cardChain.setOnClickListener {
            bindingTab.heading.text = "Tell us about your hotel"

            bindingTab.singleLayout.visibility = View.GONE
            bindingTab.propertyChainFrameLayout.visibility = View.VISIBLE

            val colorResId = R.color.subtitle // Replace with your color resource ID
            val color = ContextCompat.getColor(applicationContext, colorResId)
            bindingTab.cardSingle.strokeColor = color
            bindingTab.text1.setTextColor(color)

            val colorResId1 = R.color.primary // Replace with your color resource ID
            val color1 = ContextCompat.getColor(applicationContext, colorResId1)

            bindingTab.cardChain.strokeColor = color1
            bindingTab.text2.setTextColor(color1)
        }

        bindingTab.cardSingleNext.setOnClickListener {
            startActivity(Intent(this,SecondOnboardingScreen::class.java))
        }

        bindingTab.cardChainNext.setOnClickListener {
            val number = bindingTab.noPropertyText.text.toString().toIntOrNull() ?: 0

            if (number == 0) {
                bindingTab.noPropertyLayout.error = "Please Enter the Number of Property"
            } else {
                val intent = Intent(this, SecondOnboardingChainScreen::class.java)
                intent.putExtra("number", number)
                startActivity(intent)
            }
        }

    }
}