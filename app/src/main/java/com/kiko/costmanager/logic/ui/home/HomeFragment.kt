package com.kiko.costmanager.logic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentHomeBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.util.PrefsUtil


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefsUtil.isUserLoggedOut = false
    }
}