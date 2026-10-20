package com.example.mobile_dev_class

// Shared in-memory storage; a database is introduced in a later practical.
object TenantRepository {
    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun totalRent(): Double = tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
}
