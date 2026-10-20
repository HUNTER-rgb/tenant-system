package com.example.mobile_dev_class

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_dev_class.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            intent.getStringExtra("EMAIL")?.let { binding.emailEditText.setText(it) }
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            if (email.isEmpty() || password.isBlank()) {
                Toast.makeText(this, R.string.login_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Explicit Intent: open our own Activity and pass the user's email.
            startActivity(Intent(this, MainActivity::class.java).putExtra("EMAIL", email))
            finish()
        }
        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.helpTextView.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu")))
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(this, R.string.no_browser, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
