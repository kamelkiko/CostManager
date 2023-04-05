package com.kiko.costmanager.logic.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentSignUpBinding
import com.kiko.costmanager.logic.ui.home.HomeFragment
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.util.Constants


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignUpBinding
        get() = FragmentSignUpBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBack()
    }

    private fun addCallBack() {
        binding.textViewDoHaveAccount.setOnClickListener {
            back()
        }
        binding.toolbarSignup.setNavigationOnClickListener {
            back()
        }
        binding.btnSignup.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
        if (validate()) {
            val sharedPreferences =
                activity?.getSharedPreferences(Constants.SIGNUP_SHARED_PREF, Context.MODE_PRIVATE)
            val email = binding.editTextEmail.text.toString()
            val userName = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            val edit = sharedPreferences?.edit()
            edit?.let {
                it.putString(Constants.EMAIL, email)
                it.putString(Constants.USERNAME, userName)
                it.putString(Constants.PASSWORD, password)
            }?.apply()
            toHome()
        } else
            Toast.makeText(requireContext(), "You should fill all inputs", Toast.LENGTH_SHORT)
                .show()
    }

    private fun validate() =
        (!binding.editTextEmail.text.isNullOrEmpty() && !binding.editTextUsername.text.isNullOrEmpty()
                && !binding.editTextPassword.text.isNullOrEmpty())


    private fun toHome() {
        parentFragmentManager.commit {
            parentFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
            replace(R.id.fragment_container, HomeFragment())
            (activity as HomeActivity).bottomNavView(true)
        }
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }
}