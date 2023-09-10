package com.example.refactoringlife4.ui.home.presenter

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityHomeBinding
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModel
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModelEvent
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModelFactory
import com.example.refactoringlife4.ui.login.presenters.LoginActivity
import com.example.refactoringlife4.utils.Utils


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var backPressedTime: Long = 0
    private val backPressedInterval: Long = 2000
    private lateinit var images: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getViewModel()
        onClick()

        // Inicializa 'images' aquí, por ejemplo, cuando se obtengan del ViewModel.
        viewModel.data.observe(this) { event ->
            if (event is HomeViewModelEvent.ShowSuccessView) {
                images = event.images
            }
        }
    }

    private fun onClick() {
        binding.logOut.setOnClickListener {
            logOut()
        }
    }

    private fun getViewModel() {
        viewModel = HomeViewModelFactory(applicationContext).create(HomeViewModel::class.java)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - backPressedTime < backPressedInterval) {
            logOut()
        } else {
            Toast.makeText(this, "Presiona de nuevo para LogOut", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    }

    private fun logOut() {
        viewModel.clearUserState()
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
