package com.kiko.costmanager.logic.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kiko.costmanager.databinding.FragmentSearchBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.search.adapter.SearchAdapter
import com.kiko.costmanager.logic.ui.search.adapter.SearchInteractListener


class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchAdapter(DataManager.getAllCitiesData().take(10), this)
        binding.recycleSearch.adapter = adapter
    }

    override fun onClickItem(cityEntity: CityEntity) {
        Toast.makeText(requireContext(), cityEntity.cityName, Toast.LENGTH_SHORT).show()
    }
}