package rconnect.retvens.technologies.OnBoarding.SingleHotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityThirdOnboardingScreenBinding

class ThirdOnboardingScreen : AppCompatActivity(), ThirdOnboardingAdapter.OnItemClickListener {

    private lateinit var bindingTab:ActivityThirdOnboardingScreenBinding
    private lateinit var thirdOnboardingAdapter: ThirdOnboardingAdapter
    private  var numberLayout:ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityThirdOnboardingScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.demoBackbtn.setOnClickListener {
            onBackPressed()
        }

        bindingTab.dynamicRecycler.layoutManager = LinearLayoutManager(this)

        numberLayout.add(1)

        thirdOnboardingAdapter = ThirdOnboardingAdapter(numberLayout)
        bindingTab.dynamicRecycler.adapter = thirdOnboardingAdapter
        thirdOnboardingAdapter.notifyDataSetChanged()


        thirdOnboardingAdapter.setOnItemClickListener(this)


        bindingTab.cardSingleNext.setOnClickListener {
            startActivity(Intent(this,FourOnboardingScreen::class.java))
        }

    }

    override fun onItemClick(add: Int) {
        numberLayout.add(add)
        thirdOnboardingAdapter.notifyDataSetChanged()
    }

    override fun onRemoveItem(remove: Int) {
        numberLayout.remove(remove)
        thirdOnboardingAdapter.notifyDataSetChanged()
    }
}