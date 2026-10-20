package com.example.mobile_dev_class

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_dev_class.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton.setOnClickListener {
            val fullName = binding.fullNameEditText.text.toString().trim()
            val email = binding.registerEmailEditText.text.toString().trim()
            val password = binding.registerPasswordEditText.text.toString()
            if (fullName.isEmpty() || email.isEmpty() || password.isBlank()) {
                Toast.makeText(this, R.string.registration_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // This practical demonstrates navigation; accounts are not stored yet.
            Toast.makeText(this, R.string.account_created, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("EMAIL", email)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        binding.loginLinkTextView.setOnClickListener { finish() }
    }
}
