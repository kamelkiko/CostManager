package com.kiko.costmanager.logic.ui.seemore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentSeeMoreBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.details.DetailsFragment
import com.kiko.costmanager.logic.ui.seemore.adapter.SeeMoreAdapter
import com.kiko.costmanager.logic.ui.seemore.adapter.SeeMoreInteractListener
import com.kiko.costmanager.logic.util.Constants


class SeeMoreFragment : BaseFragment<FragmentSeeMoreBinding>(), SeeMoreInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeeMoreBinding
        get() = FragmentSeeMoreBinding::inflate

    private lateinit var adapter: SeeMoreAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    private fun setup() {
        val name = arguments?.getString(Constants.KEY)
        when (name) {
            Constants.SALARIES -> {
                binding.toolbarSeeMore.title = Constants.SALARIES
                adapter =
                    SeeMoreAdapter(DataManager.getAllInteracts().getAverageSalaryCityEntry(), this)
            }
            Constants.INTERNET -> {
                binding.toolbarSeeMore.title = Constants.INTERNET
                adapter =
                    SeeMoreAdapter(DataManager.getAllInteracts().getCitiesInternetCityEntry(), this)
            }
            Constants.DATA -> {
                binding.toolbarSeeMore.title = Constants.DATA
                adapter =
                    SeeMoreAdapter(DataManager.getAllInteracts().getCitiesHasDataQuality(), this)
            }
            Constants.JUST_FOR_YOU -> {
                binding.toolbarSeeMore.title = Constants.JUST_FOR_YOU
                adapter = SeeMoreAdapter(DataManager.getJustForYou(), this)
            }
        }
        binding.recycleSeeMore.adapter = adapter
    }

    private fun addCallBack() {
        binding.toolbarSeeMore.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onClickItem(cityEntity: CityEntity) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, DetailsFragment.newInstance(cityEntity.id))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }

    companion object {
        fun newInstance(key: String): SeeMoreFragment =
            SeeMoreFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.KEY, key)
                }
            }
    }

}