package rconnect.retvens.technologies.OnBoarding.ChainHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivitySecondOnboardingChainScreenBinding

class SecondOnboardingChainScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivitySecondOnboardingChainScreenBinding
    private lateinit var secondOnboardingChainAdapter: SecondOnboardingChainAdapter
    private  var numberLayout:ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivitySecondOnboardingChainScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        val number = intent.getIntExtra("number", 0)
        Log.e("num",number.toString())

        val numberLayout = ArrayList<Int>() // Create a list to hold the data


        bindingTab.dynamicRecycler.layoutManager = LinearLayoutManager(this)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }

        bindingTab.cardChainNext.setOnClickListener {
            startActivity(Intent(this,ThirdOnboardingChainScreen::class.java))
        }

        // Delayed creation of the adapter and setting it to the RecyclerView

            secondOnboardingChainAdapter = SecondOnboardingChainAdapter(numberLayout)
            bindingTab.dynamicRecycler.adapter = secondOnboardingChainAdapter
            secondOnboardingChainAdapter.notifyDataSetChanged()


        for (i in 0 until number) {
            numberLayout.add(1) // Add data to the list
            secondOnboardingChainAdapter.notifyDataSetChanged()
        }
    }

}