package com.kiko.costmanager.logic.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentFirstPageBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment


class FirstPageFragment : BaseFragment<FragmentFirstPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFirstPageBinding
        get() = FragmentFirstPageBinding::inflate

}