package com.kiko.costmanager.logic.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentFavouriteBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.ui.Base.BaseFragment

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBacks()
    }

    private fun setup() {
        if (DataManager.getAllFavouritesCity().isEmpty())
            showAnimation()
        else {
            hideAnimation()
        }
    }

    private fun addCallBacks() {
        binding.toolbarFavourite.setNavigationOnClickListener {
            back()
        }
    }

    private fun back() {
        requireActivity().onBackPressed()
    }

    private fun showAnimation() {
        binding.apply {
            viewLottieLayer.visibility = View.VISIBLE
            viewLottieLayer.setAnimation(R.raw.heart)
            viewLottieLayer.repeatCount = LottieDrawable.INFINITE
            viewLottieLayer.playAnimation()
            favRecycle.visibility = View.GONE
        }
    }

    private fun hideAnimation() {
        binding.apply {
            favRecycle.visibility = View.VISIBLE
            viewLottieLayer.visibility = View.GONE
        }
    }
}