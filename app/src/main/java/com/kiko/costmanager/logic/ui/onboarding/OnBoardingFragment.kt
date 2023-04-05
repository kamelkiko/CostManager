package com.kiko.costmanager.logic.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commitNow
import androidx.viewpager2.widget.ViewPager2
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentOnBoardingBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.onboarding.adapter.ViewPagerAdapter
import com.kiko.costmanager.logic.ui.login.LoginFragment
import com.kiko.costmanager.logic.util.Constants

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initProgressBar()
        addCallBack()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> changeValueOfProgressBar(1)
                    1 -> changeValueOfProgressBar(2)
                    2 -> changeValueOfProgressBar(3)
                }
            }
        })
    }

    private fun initProgressBar() {
        binding.progressBar.max = 3
        binding.progressBar.rotation = -100f
        changeValueOfProgressBar(1)
    }

    private fun initViewPager() {
        val fragments = listOf(FirstPageFragment(), SecondPageFragment(), ThirdPageFragment())
        binding.viewPager.apply {
            this.adapter = ViewPagerAdapter(fragments, parentFragmentManager, lifecycle)
            binding.dotsIndicator.attachTo(this)
        }
    }

    private fun addCallBack() {
        binding.fab.setOnClickListener {
            when (binding.viewPager.currentItem) {
                0 -> {
                    binding.viewPager.currentItem = 1
                    changeValueOfProgressBar(2)
                }
                1 -> {
                    binding.viewPager.currentItem = 2
                    changeValueOfProgressBar(3)
                }
                2 -> {
                    finishOnBoarding()
                    parentFragmentManager.commitNow {
                        replace(R.id.fragment_container, LoginFragment())
                        setReorderingAllowed(true)
                    }
                }
            }
        }
    }

    private fun changeValueOfProgressBar(value: Int) {
        binding.progressBar.progress = value
    }

    private fun finishOnBoarding() {
        val sharedPreferences =
            activity?.getSharedPreferences(Constants.ON_BOARDING_SHARED_PREF, Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.apply {
            putBoolean(Constants.FINISH_ON_BOARDING, true)
            apply()
        }
    }

}