package com.kiko.costmanager.logic.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentProfileBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.util.PrefsUtil


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefsUtil.initPrefsUtil(requireActivity())
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.apply {
            textViewEmail.text = PrefsUtil.userEmail
            textViewName.text = PrefsUtil.userName
        }
    }
}