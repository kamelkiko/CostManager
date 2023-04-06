package com.kiko.costmanager.logic.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentDetailsBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.util.Constants
import com.kiko.costmanager.logic.util.setImageUrl


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate
    private lateinit var adapter: DetailsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    private fun setup() {
        val id = arguments?.getInt(Constants.KEY)
        adapter = DetailsAdapter(DataManager.getCityById(id!!))
        binding.recycleDetails.adapter = adapter
        binding.imageDetails.setImageUrl(DataManager.getCityById(id)[0].image)
        binding.toolbarDetails.title = DataManager.getCityById(id)[0].cityName
    }

    private fun addCallBack() {
        binding.toolbarDetails.setNavigationOnClickListener {
            back()
        }
    }

    private fun back() {
        requireActivity().onBackPressed()
    }

    companion object {
        fun newInstance(id: Int): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.KEY, id)
                }
            }

    }
}