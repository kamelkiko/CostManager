package com.kiko.costmanager.logic.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentFavouriteBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate
}