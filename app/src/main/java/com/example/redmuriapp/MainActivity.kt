package com.example.redmuriapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.data.model.LatestItem
import com.example.detailpage.fragment.DetailPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), Navigator {

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

    override fun navigateFromSignInToMainPage() {
        navController.navigate(R.id.action_signInFragment_to_mainFragment)
    }

    override fun navigateFromLogInToMainPage() {
        navController.navigate(R.id.action_logInFragment_to_mainFragment)
    }

    override fun navigateFromSignInToLogInPage() {
        navController.navigate(R.id.action_signInFragment_to_logInFragment)
    }

    override fun navigateFromProfileToSignInPage() {
        navController.navigate(R.id.action_profileFragment_to_signInFragment)
    }

    override fun navigateFromMainToDetailPage(item: LatestItem) {
        navController.navigate(
            R.id.action_mainFragment_to_detailFragment,
            DetailPageFragment.createBundle(item)
        )
    }

    companion object {

        const val IS_LOGGED = "is_logged"
        const val USER_FIRST_NAME = "user_first_name"
    }
}

