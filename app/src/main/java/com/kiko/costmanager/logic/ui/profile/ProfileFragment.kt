package com.kiko.costmanager.logic.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentProfileBinding
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.favourite.FavouriteFragment
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
            textViewEmail.text = PrefsUtil.userEmail
            textViewName.text = PrefsUtil.userName
        }
    }

    private fun addCallBacks() {
        binding.btnFavourite.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, FavouriteFragment())
                addToBackStack(null)
                setReorderingAllowed(true)
                (activity as HomeActivity).bottomNavView(false)
            }
        }
        binding.textViewContactus.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(MY_FACE_ACCOUNT)
            startActivity(intent)
        }
    }

    companion object {
        const val MY_FACE_ACCOUNT = "https://www.facebook.com/kamelmohamed880"
    }
}