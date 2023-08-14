package rconnect.retvens.technologies.Authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rconnect.retvens.technologies.R
import rconnect.retvens.technologies.databinding.ActivityResetPasswordScreenBinding

class ResetPasswordScreen : AppCompatActivity() {

    private lateinit var bindingTab:ActivityResetPasswordScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTab = ActivityResetPasswordScreenBinding.inflate(layoutInflater)
        setContentView(bindingTab.root)

        bindingTab.forgotBackbtn.setOnClickListener {
            onBackPressed()
        }
    }
}