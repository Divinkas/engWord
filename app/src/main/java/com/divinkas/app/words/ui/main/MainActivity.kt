package com.divinkas.app.words.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ActivityMainBinding
import com.divinkas.app.words.helper.ext.binding
import com.divinkas.app.words.ui.AbstractScreenActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractScreenActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        bottom_nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.wordScreen -> findNavController(R.id.nav_graph_fragment).setGraph(R.navigation.main_nav_graph)
                R.id.learnScreen -> findNavController(R.id.nav_graph_fragment).setGraph(R.navigation.learn_nav_graph)
                R.id.statisticScreen -> findNavController(R.id.nav_graph_fragment).setGraph(R.navigation.statistic_nav_graph)
                R.id.settingScreen -> findNavController(R.id.nav_graph_fragment).setGraph(R.navigation.setting_nav_graph)
            }
            true
        }
    }
}
