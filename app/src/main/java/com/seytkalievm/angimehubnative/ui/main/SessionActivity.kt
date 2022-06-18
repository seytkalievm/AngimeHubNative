package com.seytkalievm.angimehubnative.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.ActivitySessionBinding

class SessionActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var toolBar: Toolbar
    private lateinit var binding: ActivitySessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySessionBinding.inflate(layoutInflater).apply {
            toolBar = mainActivityToolbar
            bottomNavBar = bottomNavMenu
            toolBar.setTitle(R.string.stand_up_shows)
            setSupportActionBar(toolBar)
            setContentView(root)
        }
        navController = Navigation.findNavController(this, R.id.main_activity_host_fragment)
        NavigationUI.setupWithNavController(bottomNavBar, navController)
    }
}