package com.kiko.costmanager.logic.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentLoginBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.home.HomeFragment
import com.kiko.costmanager.logic.ui.signup.SignUpFragment
import com.kiko.costmanager.logic.util.PrefsUtil


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBack()
        PrefsUtil.initPrefsUtil(requireActivity())
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
            val userName = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (userName.isEmpty() && password.isEmpty())
                Toast.makeText(requireContext(), "You have to fill all inputs", Toast.LENGTH_SHORT)
                    .show()
            else {
                if (PrefsUtil.userEmail.equals(userName) && PrefsUtil.userPassword.equals(password)) {
                    parentFragmentManager.commit {
                        replace(R.id.fragment_container, HomeFragment())
                        setReorderingAllowed(true)
                        (activity as HomeActivity).bottomNavView(true)
                    }
                } else
                    Toast.makeText(
                        requireContext(),
                        "Wrong name or password!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
            }
        }
        binding.textViewForgotPassword.setOnClickListener {
            if (PrefsUtil.userPassword != "")
                Toast.makeText(
                    requireContext(),
                    PrefsUtil.userPassword,
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(
                    requireContext(),
                    "You should have account first",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
}