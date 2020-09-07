package com.divinkas.app.words.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ActivityMainBinding
import com.divinkas.app.words.helper.ext.binding
import com.divinkas.app.words.ui.base.AbstractScreenActivity

class MainActivity : AbstractScreenActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navController = findNavController(R.id.nav_graph_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }

        binding.navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else run {
            super.onBackPressed()
        }
    }
}
