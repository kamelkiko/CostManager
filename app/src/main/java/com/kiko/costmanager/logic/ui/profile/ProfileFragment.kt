package com.kiko.costmanager.logic.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentProfileBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.login.LoginFragment
import com.kiko.costmanager.logic.util.PrefsUtil


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefsUtil.initPrefsUtil(requireActivity())
        setup()
        addCallBacks()
    }

    private fun setup() {
        (activity as HomeActivity).bottomNavView(true)
        binding.apply {
            textViewEmail.text = PrefsUtil.userName
            textViewName.text = PrefsUtil.userEmail
        }
    }

    private fun addCallBacks() {
        binding.textViewContactus.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(MY_FACE_ACCOUNT)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            parentFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.commit {
                replace(R.id.fragment_container, LoginFragment())
                setReorderingAllowed(true)
            }
        }
        binding.btnDelete.setOnClickListener {
            PrefsUtil.clearAccount()
            parentFragmentManager.commit {
                replace(R.id.fragment_container, LoginFragment())
                setReorderingAllowed(true)
            }
        }
    }

    companion object {
        const val MY_FACE_ACCOUNT = "https://www.facebook.com/kamelmohamed880"
    }
}