package com.smartshopper.smart_shopper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.room.Room
import com.google.android.material.tabs.TabLayout
import com.smartshopper.smart_shopper.R
import com.smartshopper.smart_shopper.database.AppDatabase
import com.smartshopper.smart_shopper.databinding.ActivityMainBinding
import com.smartshopper.smart_shopper.utils.Constant
import com.smartshopper.smart_shopper.utils.SingleTon
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var db: AppDatabase
    var tabList = arrayListOf("Products", "Deals", "Buy Again", "Grocery List", "All Product")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        setTabLayout()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, Constant.GROCERY
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        if (db.Dao().getUser() != null && (!db.Dao().getUser().name.isNullOrEmpty())) {
            navController.navigate(R.id.productFragment)
            showName()
        }
    }

    private fun setTabLayout() = binding.run {
        for (item in tabList) {
            tabs.addTab(tabs.newTab().setText(item))
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (db.Dao().getUser() != null && (!db.Dao().getUser().name.isNullOrEmpty())) {
                    showName()
                    when (tabs.selectedTabPosition) {
                        0 -> {
                            navController.navigate(R.id.productFragment)
                        }
                        1 -> {
                            navController.navigate(R.id.dealsFragment)
                        }
                        2 -> {
                            navController.navigate(R.id.buyAgainFragment)
                        }
                        3 -> {
                            navController.navigate(R.id.groceryListFragment)
                        }
                        4 -> {
                            navController.navigate(R.id.allProductFragment)
                        }
                    }
                } else {
                    MotionToast.createColorToast(
                        this@MainActivity,
                        "Warning",
                        "Setup Profile first",
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(this@MainActivity, R.font.proximanovacond_medium)
                    )
                }

            }
        })
    }

    fun showName() {
        binding.name.isVisible = true
        binding.starImg.isVisible = false
        binding.name.text =
            db.Dao().getUser().name?.substring(0, 1)?.substringAfter("")?.substring(0, 1)
    }

    fun selectProductTab(position : Int){
        binding.tabs.selectTab(binding.tabs.getTabAt(position))
    }
}