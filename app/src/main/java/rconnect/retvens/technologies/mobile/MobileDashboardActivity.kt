package rconnect.retvens.technologies.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import rconnect.retvens.technologies.R

class MobileDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_dashboard)

        replaceFragment(DashboardMobileFragment())

    }
    private fun replaceFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dashboard_fragment_container,fragment)
            transaction.commit()
        }
    }

}