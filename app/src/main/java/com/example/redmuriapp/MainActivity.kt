package com.example.redmuriapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.example.redmuriapp.fragments.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkUserSigned()
        setupBottomNavigation()
    }

    private fun checkUserSigned() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val isRegistered = pref.getBoolean("isRegistered", false)
        if (!isRegistered) {
            navController.navigate(R.id.action_mainFragment_to_signInFragment)
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        NavigationUI.setupWithNavController(bottomNav, navController)
    }
}