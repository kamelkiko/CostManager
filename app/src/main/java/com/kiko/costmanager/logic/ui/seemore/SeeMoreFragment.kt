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
        adapter = SeeMoreAdapter(DataManager.getAllCitiesData(), this)
        binding.recycleSeeMore.adapter = adapter
    }

    private fun addCallBack() {
        binding.toolbarSeeMore.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getLayoutView() {

    }

    override fun onClickItem(cityEntity: CityEntity) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, DetailsFragment.newInstance(cityEntity.id))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }
}