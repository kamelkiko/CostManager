package com.kiko.costmanager.logic.ui.Base


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.ActivityHomeBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.ui.home.HomeFragment
import com.kiko.costmanager.logic.ui.login.LoginFragment
import com.kiko.costmanager.logic.ui.onboarding.OnBoardingFragment
import com.kiko.costmanager.logic.ui.rank.RankFragment
import com.kiko.costmanager.logic.util.CsvParser
import com.kiko.costmanager.logic.util.PrefsUtil
import java.io.BufferedReader
import java.io.InputStreamReader


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PrefsUtil.initPrefsUtil(this)
        setup()
        addCallBack()
    }

    private fun setup() {
        getSharedPrefOnBoarding()
        openAndParseFile()
    }

    private fun openAndParseFile() {
        val inputStream = this.assets.open(FILE_NAME)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val csvParser = CsvParser()
        buffer.forEachLine {
            val city = csvParser.parseLine(it)
            DataManager.addCity(city)
        }
    }

    private fun getSharedPrefOnBoarding() {
        val isOnBoardingFinished = PrefsUtil.onBoardingFinish
        if (isOnBoardingFinished!!) getSharedPrefUser()
        else showOnBoarding()
    }

    private fun getSharedPrefUser() {
        val email = PrefsUtil.userEmail
        val userName = PrefsUtil.userName
        val password = PrefsUtil.userPassword
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

    companion object {
        private const val FILE_NAME = "costOfLiving.csv"
    }
}
