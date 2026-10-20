package com.example.mobile_dev_class

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_dev_class.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.getString("TENANT_NAME")?.let { name ->
            lastTenant = Tenant(name, savedInstanceState.getString("TENANT_PHONE", ""),
                savedInstanceState.getString("TENANT_RENT", ""))
            binding.tenant = lastTenant
        }
        if (savedInstanceState == null) {
            intent.getStringExtra("EMAIL")?.let {
                Toast.makeText(this, getString(R.string.logged_in_as, it), Toast.LENGTH_SHORT).show()
            }
        }
        binding.callButton.setOnClickListener {
            val tenant = savedTenantOrNotify() ?: return@setOnClickListener
            openExternal(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tenant.phone, null)))
        }
        binding.shareButton.setOnClickListener {
            val tenant = savedTenantOrNotify() ?: return@setOnClickListener
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, tenant.summary())
            }
            openExternal(Intent.createChooser(intent, getString(R.string.share_tenant)))
        }
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = getString(R.string.tenant_name_required)
                binding.tenantNameEditText.requestFocus()
                return@setOnClickListener
            }

            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            if (phone.isEmpty()) {
                binding.phoneEditText.error = getString(R.string.required)
                binding.phoneEditText.requestFocus()
                return@setOnClickListener
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = getString(R.string.required)
                binding.rentEditText.requestFocus()
                return@setOnClickListener
            }
            lastTenant = Tenant(name, phone, rent)
            binding.tenant = lastTenant

            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
            binding.tenantNameEditText.requestFocus()
        }
    }
    private fun savedTenantOrNotify(): Tenant? {
        if (lastTenant == null) {
            Toast.makeText(this, R.string.save_tenant_first, Toast.LENGTH_SHORT).show()
        }
        return lastTenant
    }

    private fun openExternal(intent: Intent) {
        try {
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, R.string.no_matching_app, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onSaveInstanceState")
        lastTenant?.let {
            outState.putString("TENANT_NAME", it.name)
            outState.putString("TENANT_PHONE", it.phone)
            outState.putString("TENANT_RENT", it.rent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}
