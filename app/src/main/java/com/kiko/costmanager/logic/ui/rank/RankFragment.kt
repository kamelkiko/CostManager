package com.kiko.costmanager.logic.ui.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentRankBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.chart.ChartFragment
import com.kiko.costmanager.logic.ui.rank.adapter.RankAdapter
import com.kiko.costmanager.logic.ui.rank.adapter.RankInteractListener


class RankFragment : BaseFragment<FragmentRankBinding>(), RankInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRankBinding
        get() = FragmentRankBinding::inflate
    private lateinit var adapter: RankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).bottomNavView(true)
        binding.recycleRank.adapter = adapter
    }

    private fun setUp() {
        adapter = RankAdapter(DataManager.getAllCategory(), this)
    }

    override fun onClickItem(category: Category) {
        (activity as HomeActivity).bottomNavView(false)
        parentFragmentManager.commit {
            replace(R.id.fragment_container, ChartFragment.newInstance(category.categoryName))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }

}