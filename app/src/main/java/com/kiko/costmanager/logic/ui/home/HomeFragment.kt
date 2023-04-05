package com.kiko.costmanager.logic.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentHomeBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
}