package io.github.nirajprakash.patterns.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.R
import io.github.nirajprakash.patterns.databinding.MainActivityBinding
import io.github.nirajprakash.patterns.modules.account.AccountConstants
import io.github.nirajprakash.patterns.tools.livedata.LiveDataObserver
import io.github.nirajprakash.patterns.ui.nav.NavManager
import io.github.nirajprakash.patterns.ui.pages.auth.AuthActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navManager: NavManager

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding


    private val mViewModel by viewModels<MainViewModel>()

    private val mActivityResultAuth =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            info{"mActivityResultSignin: ${result.data}"}
//            onAuthenticationResult(result.data, result.resultCode)
        }

    override fun onCreate(savedInstanceState: Bundle?) {

//        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

//        installSplashScreen()
//        setTheme(R.style.Theme_Taru)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController() ?: return

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        navController.addOnDestinationChangedListener { _, destination, _ ->

            Log.d("MainActivity", "addOnDestinationChangedListener: ${destination.id}")
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
        setupViewModelObservers()
/*
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/


    }

    override fun onStart() {
        super.onStart()
        Log.d("Main Activity: ", "onStart " )
        attachNavManager()
    }

    override fun onStop() {
        super.onStop()
        Log.d("Main Activity: ", "onStop " )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /* ***********************************************************************************************************************
     *                                                                                                                  Activity
     */

    private fun startActivityAuth() {
        val i = Intent(this, AuthActivity::class.java)

        Log.d("Main Activity: ", "startActivityAuth " )
//        i.putExtra(
//            AccountConstants.Account.Arguments.ACCOUNT_ACTION,
//            AccountConstants.Account.ACTION_SIGNIN
//        )
        mActivityResultAuth.launch(i)
    }

    /* ********************************************************************************************
     *                                                                                                              Observer
     */

    fun setupViewModelObservers() {

        mViewModel.mEventStartAuthActivity.observe(this, LiveDataObserver{
            startActivityAuth()
        })
    }


    /* ***********************************************************************************************************************
     *                                                                                                              Other functions
     */

    private fun findNavController(): NavController? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
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