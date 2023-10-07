package io.github.nirajprakash.patterns.ui.pages.auth

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.R
import io.github.nirajprakash.patterns.databinding.AuthActivityBinding
import io.github.nirajprakash.patterns.databinding.MainActivityBinding
import io.github.nirajprakash.patterns.ui.nav.NavManager
import javax.inject.Inject

/**
 * Created by Niraj on 03-10-2023.
 */
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    @Inject
    lateinit var navManager: NavManager
    private lateinit var binding: AuthActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setTheme(R.style.Theme_Patterns_Transparent)
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
        binding = AuthActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController() ?: return

        val navGraph = navController.navInflater.inflate(R.navigation.nav_auth)
        navController.graph = navGraph
        Log.d("Auth Activity", "navGraph: ${navGraph.toString()}")

        navController.addOnDestinationChangedListener { _, destination, _ ->

            Log.d("Auth Activity", "addOnDestinationChangedListener: ${destination.id}")
            /*when (destination.id) {
                R.id.nav_home -> binding.navView.visibility = View.VISIBLE
                R.id.nav_plants -> binding.navView.visibility = View.VISIBLE
                R.id.nav_about -> binding.navView.visibility = View.VISIBLE
                R.id.nav_cure -> binding.navView.visibility = View.VISIBLE
                else -> binding.navView.visibility = View.GONE
            }*/
        }

//        binding.navView.setupWithNavController(navController)

//        initNavManager()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Auth Activity: ", "onStart " )
        attachNavManager()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Auth Activity: ", "onStop " )

    }

    private fun findNavController(): NavController? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

        Log.d("Auth Activity: ", "findNavController ${navHostFragment?.navController} " )
        return navHostFragment?.navController
    }
    private fun attachNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            navHostFragment?.findNavController()?.navigate(it)

            /* val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
             currentFragment?.navigateSafe(it)*/
        }
    }
}