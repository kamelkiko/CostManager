package com.kiko.costmanager.logic.ui.Base


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.ActivityHomeBinding
import com.kiko.costmanager.logic.data.dataSource.CsvDataSource
import com.kiko.costmanager.logic.data.dataSource.DataSourceImpl
import com.kiko.costmanager.logic.data.dataSource.DataSourceProvider
import com.kiko.costmanager.logic.ui.home.HomeFragment
import com.kiko.costmanager.logic.ui.login.LoginFragment
import com.kiko.costmanager.logic.ui.onboarding.OnBoardingFragment
import com.kiko.costmanager.logic.ui.rank.RankFragment
import com.kiko.costmanager.logic.util.Constants
import com.kiko.costmanager.logic.util.CsvParser


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dataSourceProvider: DataSourceProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        initDataManager(savedInstanceState)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        addCallBack()
    }

    private fun setup() {
        getSharedPrefOnBoarding()
    }

    private fun getSharedPrefOnBoarding() {
        val sharedPreferences =
            getSharedPreferences(Constants.ON_BOARDING_SHARED_PREF, Context.MODE_PRIVATE)
        val isOnBoardingFinished =
            sharedPreferences.getBoolean(Constants.FINISH_ON_BOARDING, false)
        if (isOnBoardingFinished) getSharedPrefUser()
        else showOnBoarding()
    }

    private fun getSharedPrefUser() {
        val sharedPreferences =
            getSharedPreferences(Constants.SIGNUP_SHARED_PREF, Context.MODE_PRIVATE)
        val email = sharedPreferences.getString(Constants.EMAIL, "")
        val userName = sharedPreferences.getString(Constants.USERNAME, "")
        val password = sharedPreferences.getString(Constants.PASSWORD, "")
        if (email != "" && userName != "" && password != "")
            showHome()
        else
            showLogIn()
    }

    private fun showOnBoarding() {
        supportFragmentManager.commitNow {
            replace(R.id.fragment_container, OnBoardingFragment())
            setReorderingAllowed(true)
        }
    }

    private fun showLogIn() {
        supportFragmentManager.commitNow {
            replace(R.id.fragment_container, LoginFragment())
            setReorderingAllowed(true)
        }
    }

    private fun showHome() {
        bottomNavView(true)
        binding.navBottom.selectedItemId = R.id.nav_home
        supportFragmentManager.commitNow {
            replace(R.id.fragment_container, HomeFragment())
            setReorderingAllowed(true)
        }
    }

    private fun addCallBack() {
        binding.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, HomeFragment())
                        setReorderingAllowed(true)
                    }
                    true
                }
                R.id.nav_search -> {
                    true
                }
                R.id.nav_ranks -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, RankFragment())
                        setReorderingAllowed(true)
                    }
                    true
                }
                R.id.nav_favourite -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun bottomNavView(visible: Boolean) {
        if (visible)
            binding.navBottom.visibility = View.VISIBLE
        else
            binding.navBottom.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_DATA_MANAGER, dataSourceProvider)
    }


    private fun initDataManager(savedInstanceState: Bundle?) {
        dataSourceProvider = when (savedInstanceState) {
            null -> DataSourceImpl(CsvDataSource(CsvParser(), this))
            else -> savedInstanceState.getSerializable(KEY_DATA_MANAGER) as DataSourceProvider
        }
    }

    fun getDataSource() = dataSourceProvider

    companion object {
        const val KEY_DATA_MANAGER = "DATA_MANAGER"
    }
}
