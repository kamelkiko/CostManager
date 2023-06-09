package com.kiko.costmanager.logic.ui.Base


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.ActivityHomeBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.ui.chart.ChartFragment
import com.kiko.costmanager.logic.ui.details.DetailsFragment
import com.kiko.costmanager.logic.ui.home.HomeFragment
import com.kiko.costmanager.logic.ui.login.LoginFragment
import com.kiko.costmanager.logic.ui.onboarding.OnBoardingFragment
import com.kiko.costmanager.logic.ui.profile.ProfileFragment
import com.kiko.costmanager.logic.ui.rank.RankFragment
import com.kiko.costmanager.logic.ui.search.SearchFragment
import com.kiko.costmanager.logic.ui.seemore.SeeMoreFragment
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
        var id = 0
        buffer.forEachLine {
            val city = csvParser.parseLine(++id, it)
            DataManager.addCity(city)
        }
    }

    private fun getSharedPrefOnBoarding() {
        val isOnBoardingFinished = PrefsUtil.isOnBoardingFinished
        if (isOnBoardingFinished!!) getSharedPrefUser()
        else showOnBoarding()
    }

    private fun getSharedPrefUser() {
        val email = PrefsUtil.userEmail
        val userName = PrefsUtil.userName
        val password = PrefsUtil.userPassword
        if (email != "" && userName != "" && password != "")
            if (PrefsUtil.isUserLoggedOut == true)
                showLogIn()
            else
                showHome()
        else
            showLogIn()
    }

    private fun showOnBoarding() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, OnBoardingFragment())
            setReorderingAllowed(true)
        }
    }

    private fun showLogIn() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, LoginFragment())
            setReorderingAllowed(true)
        }
    }

    private fun showHome() {
        bottomNavView(true)
        setFragment(HomeFragment(), TAG_HOME_FRAGMENT)
        setSelectedHome()
    }

    private fun addCallBack() {
        binding.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setFragment(HomeFragment(), TAG_HOME_FRAGMENT)
                    true
                }
                R.id.nav_search -> {
                    setFragment(SearchFragment(), TAG_SEARCH_FRAGMENT)
                    true
                }
                R.id.nav_ranks -> {
                    setFragment(RankFragment(), TAG_RANK_FRAGMENT)
                    true
                }
                R.id.nav_profile -> {
                    setFragment(ProfileFragment(), TAG_PROFILE_FRAGMENT)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (currentFragment == null || currentFragment.javaClass != fragment.javaClass)
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment, tag)
                .commit()
    }

    fun bottomNavView(visible: Boolean) {
        if (visible)
            binding.navBottom.visibility = View.VISIBLE
        else
            binding.navBottom.visibility = View.GONE
    }

    companion object {
        private const val FILE_NAME = "costOfLiving.csv"
        const val TAG_HOME_FRAGMENT = "Home"
        const val TAG_RANK_FRAGMENT = "Rank"
        const val TAG_SEARCH_FRAGMENT = "Search"
        const val TAG_PROFILE_FRAGMENT = "Profile"
    }

    override fun onDestroy() {
        super.onDestroy()
        DataManager.clearCity()
    }

    override fun onBackPressed() {
        when (val currentFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainer.id)) {
            is HomeFragment -> finish()
            is RankFragment, is SearchFragment, is ProfileFragment -> {
                setFragment(HomeFragment(), TAG_HOME_FRAGMENT)
                clearBackStack()
                binding.navBottom.selectedItemId = R.id.nav_home
            }
            is ChartFragment, is DetailsFragment, is SeeMoreFragment -> {
                supportFragmentManager.popBackStack()
            }
            else -> {
                supportFragmentManager.popBackStackImmediate()
                val tag = currentFragment?.tag
                binding.navBottom.selectedItemId = getSelectedItemId(tag)
            }
        }
    }

    private fun getSelectedItemId(tag: String?): Int {
        return when (tag) {
            TAG_HOME_FRAGMENT -> R.id.nav_home
            TAG_RANK_FRAGMENT -> R.id.nav_ranks
            TAG_SEARCH_FRAGMENT -> R.id.nav_search
            TAG_PROFILE_FRAGMENT -> R.id.nav_profile
            else -> R.id.nav_home
        }
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun setSelectedHome() {
        binding.navBottom.selectedItemId = R.id.nav_home
    }
}
