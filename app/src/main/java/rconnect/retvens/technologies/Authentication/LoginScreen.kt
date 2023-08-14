package rconnect.retvens.technologies.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.forgotPassText.setOnClickListener {
            startActivity(Intent(this,ResetPasswordScreen::class.java))
        }

        bindingTab.demoCard.setOnClickListener {
            startActivity(Intent(this,RequestDemoScreen::class.java))
        }
    }
}