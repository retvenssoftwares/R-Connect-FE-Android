package rconnect.retvens.technologies.Dashboard

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var bindingTab:ActivityDashboardBinding
    private lateinit var toggle: ActionBarDrawerToggle
    var click = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)



        replaceFragment(DashboardFragment())


        toggle = ActionBarDrawerToggle(this,bindingTab.drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        bindingTab.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set the toolbar as the support action bar
        setSupportActionBar(bindingTab.toolbar)

        // Enable the up button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_table_rows_24)
        // Set click listener for the up button


        val header = LayoutInflater.from(this).inflate(R.layout.nav_header_dashboard,bindingTab.navView,false)
        bindingTab.navView.addHeaderView(header)


        bindingTab.navView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.partner -> Toast.makeText(applicationContext,"click", Toast.LENGTH_SHORT).show()
                R.id.tutorial ->  Toast.makeText(applicationContext,"click", Toast.LENGTH_SHORT).show()

            }
            true
        }

        bindingTab.toolbar.setNavigationOnClickListener {
            if (bindingTab.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                bindingTab.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                bindingTab.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }

}