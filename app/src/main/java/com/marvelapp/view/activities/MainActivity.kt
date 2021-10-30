package com.marvelapp.view.activities

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.marvelapp.R
import com.marvelapp.core.activities.BaseActivity
import com.marvelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navigationController: NavController

    override fun initViews(){

        initNavigation()
    }

    private fun initNavigation(){

        navigationController = Navigation.findNavController(this@MainActivity, R.id.fragment)
        NavigationUI.setupWithNavController(binding.toolbar, navigationController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController, null)
    }
}