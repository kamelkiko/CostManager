package com.kiko.costmanager.logic.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentLoginBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.signup.SignUpFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBack()
    }

    private fun addCallBack() {
        binding.textViewDontHaveAccount.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, SignUpFragment())
                    .addToBackStack(null)
                setReorderingAllowed(true)
            }
        }
        binding.btnLogin.setOnClickListener {
        }
    }
}