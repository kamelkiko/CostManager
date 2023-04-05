package com.kiko.costmanager.logic.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentThirdPageBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment


class ThirdPageFragment : BaseFragment<FragmentThirdPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentThirdPageBinding
        get() = FragmentThirdPageBinding::inflate

}