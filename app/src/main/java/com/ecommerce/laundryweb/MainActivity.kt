package com.ecommerce.laundryweb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ecommerce.laundryweb.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        const val DRAWER_GRAVITY = GravityCompat.START
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomAppBarNavigation.bottomNavView.apply {
            // We don't want to apply custom TINT to the icons, as colors are already defined in the resrouces
            itemIconTintList = null
            // BottomNavView has a menu item at center with empty text, it needs to be disabled to avoid click ripples
            menu[2].isEnabled = false
        }

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_settings -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    binding.drawerLayout.closeDrawer(DRAWER_GRAVITY)
                    false
                }
                else -> true
            }
        }


        binding.bottomAppBarNavigation.fabNewOrder.setOnClickListener {
            binding.drawerLayout.showSnack("New order support will be added soon")
        }
    }

    fun drawerToggle() {
        binding.drawerLayout.let { drawer ->
            if(drawer.isDrawerOpen(DRAWER_GRAVITY))
                drawer.closeDrawer(DRAWER_GRAVITY)
            else
                drawer.openDrawer(DRAWER_GRAVITY)
        }
    }

    override fun onBackPressed() {
        val drawerLayout = binding.drawerLayout
        if(drawerLayout.isDrawerOpen(DRAWER_GRAVITY)) {
            drawerLayout.closeDrawer(DRAWER_GRAVITY)
        } else
            super.onBackPressed()
    }

    fun listItemClicked(itemTitle: String) {
        binding.drawerLayout.showSnack("`$itemTitle` support will be added soon!")
    }
}