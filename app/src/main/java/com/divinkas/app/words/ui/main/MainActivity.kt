package com.divinkas.app.words.ui.main

import android.os.Bundle
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ActivityMainBinding
import com.divinkas.app.words.helper.ext.binding
import com.divinkas.app.words.ui.AbstractScreenActivity

class MainActivity : AbstractScreenActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding(R.layout.activity_main)
        initView()
    }

    private fun initView() {
    }
}
