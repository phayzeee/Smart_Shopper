package com.smartshopper.smart_shopper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var tabList = arrayListOf("Products", "Deals", "Buy Again", "Grocery List", "All Product")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        setTabLayout()

    }

    private fun setTabLayout() = binding.run {
        for (item in tabList) {
            tabs.addTab(tabs.newTab().setText(item))
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                showName()
                when (tabs.selectedTabPosition) {
                    0 -> {
                        navController.navigate(R.id.productFragment)
                    }
                    1 -> {
                        navController.navigate(R.id.dealsFragment)
                    }
                }
            }
        })
    }

    fun showName() {
        binding.name.isVisible = true
        binding.starImg.isVisible = false
    }
}