package com.kiko.costmanager.logic.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentSecondPageBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment


class SecondPageFragment : BaseFragment<FragmentSecondPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSecondPageBinding
        get() = FragmentSecondPageBinding::inflate


}