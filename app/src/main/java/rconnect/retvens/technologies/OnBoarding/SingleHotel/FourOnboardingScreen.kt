package rconnect.retvens.technologies.OnBoarding.SingleHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityFourOnboardingScreenBinding

class FourOnboardingScreen : AppCompatActivity(), FourOnboardingAdapter.OnItemClickListener {

    private lateinit var bindingTab:ActivityFourOnboardingScreenBinding
    private lateinit var fourOnboardingAdapter: FourOnboardingAdapter
    private var numberLayout:ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityFourOnboardingScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }

        bindingTab.dynamicRecycler.layoutManager = LinearLayoutManager(this)

        numberLayout.add(1)

        fourOnboardingAdapter = FourOnboardingAdapter(numberLayout)
        bindingTab.dynamicRecycler.adapter = fourOnboardingAdapter
        fourOnboardingAdapter.notifyDataSetChanged()

        fourOnboardingAdapter.setOnItemClickListener(this)

        bindingTab.cardSingleNext.setOnClickListener {
            startActivity(Intent(this,FinalOnboardingScreen::class.java))
        }
    }

    override fun onItemClick(add: Int) {
        numberLayout.add(add)
        fourOnboardingAdapter.notifyDataSetChanged()
    }

    override fun onRemoveItem(remove: Int) {
        numberLayout.remove(remove)
        fourOnboardingAdapter.notifyDataSetChanged()
    }
}