package com.seytkalievm.angimehubnative.presentation.session

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.ActivitySessionBinding
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "SessionActivity"

@AndroidEntryPoint
class SessionActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var toolBar: Toolbar
    private lateinit var binding: ActivitySessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySessionBinding.inflate(layoutInflater).apply {
            toolBar = mainActivityToolbar
            toolBar.setTitle(R.string.stand_up_shows)
            setSupportActionBar(toolBar)
            bottomNavBar = bottomNavMenu
            setContentView(root)
        }
        navController = Navigation.findNavController(this, R.id.session_activity_host_fragment)
        NavigationUI.setupWithNavController(bottomNavBar, navController)

        bottomNavBar.setOnItemReselectedListener { item ->
            binding.sessionShowProfileIb.visibility = VISIBLE
            navController.popBackStack(item.itemId, inclusive = false)
        }

        bottomNavBar.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            binding.sessionShowProfileIb.visibility = VISIBLE
            navController.popBackStack(item.itemId, false)
            true
        }

        binding.sessionShowProfileIb.setOnClickListener { showProfile() }
    }

    override fun onBackPressed() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        bottomNavBar.visibility = VISIBLE
        binding.sessionShowProfileIb.visibility = VISIBLE
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun BottomNavigationView.uncheckAllItems() {
        menu.setGroupCheckable(0, true, false)
        for (i in 0 until menu.size()) {
            menu.getItem(i).isChecked = false
        }
        menu.setGroupCheckable(0, true, true)
    }

    private fun showProfile(){
        toolBar.setTitle(R.string.profile)
        bottomNavBar.uncheckAllItems()
        binding.sessionShowProfileIb.visibility = GONE
        navController.navigate(R.id.profileFragment)
    }


    fun updateToolbar(title: String, supportNavigateBack: Boolean){
        toolBar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(supportNavigateBack)
        binding.sessionShowProfileIb.visibility = GONE
    }
}