package com.example.redmuriapp.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNav()
        checkUserSigned()
    }

    private fun checkUserSigned() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val isRegistered = pref.getBoolean(IS_LOGGED, false)
        if (!isRegistered) {
            navController.navigate(R.id.action_mainFragment_to_signInFragment)
        }
    }

    private fun setupNav() {
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> showBottomNav()
                R.id.detailFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.visibility = View.GONE
    }

    companion object {

        const val IS_LOGGED = "is_logged"
        const val USER_FIRST_NAME = "user_first_name"
    }
}

