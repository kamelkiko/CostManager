package com.kiko.costmanager.logic.ui.rank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiko.costmanager.databinding.FragmentRankBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.rank.adapter.RankAdapter
import com.kiko.costmanager.logic.ui.rank.adapter.RankInteractListener


class RankFragment : BaseFragment<FragmentRankBinding>(), RankInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRankBinding
        get() = FragmentRankBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            dataSourceProvider.getAllCities().take(10).let { RankAdapter(it, this@RankFragment) }
        binding.recycleRank.adapter = adapter
        dataSourceProvider.getAllCities()[0].let { Log.d("TAG", it.cityName) }
    }

    override fun onClickItem(cityEntity: CityEntity) {

    }
}