package com.rmaprojects.submission1.pages.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var appbarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        appbarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_list_story, R.id.nav_login
            )
        )

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHost.navController

        setupActionBarWithNavController(navController, appbarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val showFab: Boolean = when (destination.id) {
                R.id.nav_list_story -> {
                    true
                }
                else -> {
                    false
                }
            }

            binding.fabAddStory.isVisible = showFab
        }

        binding.fabAddStory.setOnClickListener {
            navController.navigate(R.id.action_nav_list_story_to_uploadStoryFragment)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appbarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }
}