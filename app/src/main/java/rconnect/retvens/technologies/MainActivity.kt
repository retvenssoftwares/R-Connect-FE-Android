package rconnect.retvens.technologies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import rconnect.retvens.technologies.Authentication.LoginScreen
import rconnect.retvens.technologies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingTab: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        val handler = Handler()

        handler.postDelayed({
            startActivity(Intent(this,LoginScreen::class.java))
        },1000)

    }
}