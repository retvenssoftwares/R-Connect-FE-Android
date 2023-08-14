package rconnect.retvens.technologies.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import rconnect.retvens.technologies.OnBoarding.FirstOnBoardingScreen
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityRequestDemoScreenBinding

class RequestDemoScreen : AppCompatActivity() {

    private lateinit var bindingTab: ActivityRequestDemoScreenBinding
    private var click:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityRequestDemoScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }


        bindingTab.rowncheckCard.setOnClickListener {
            click = !click
            if (click){
                bindingTab.ImportLayout.visibility = View.VISIBLE
                bindingTab.AccontLayout.visibility = View.GONE
                bindingTab.selectImport.visibility = View.VISIBLE
            }else{
                bindingTab.ImportLayout.visibility = View.GONE
                bindingTab.AccontLayout.visibility = View.VISIBLE
                bindingTab.selectImport.visibility = View.GONE
            }
        }

        bindingTab.cardCreateAccount.setOnClickListener {
            startActivity(Intent(this,FirstOnBoardingScreen::class.java))
        }


    }
}