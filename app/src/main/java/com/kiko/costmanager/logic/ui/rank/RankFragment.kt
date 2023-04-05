package com.kiko.costmanager.logic.ui.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.kiko.costmanager.databinding.FragmentRankBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.rank.adapter.RankAdapter
import com.kiko.costmanager.logic.ui.rank.adapter.RankInteractListener


class RankFragment : BaseFragment<FragmentRankBinding>(), RankInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRankBinding
        get() = FragmentRankBinding::inflate
    private lateinit var adapter: RankAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        addCallBack()
    }

    private fun addCallBack() {
        binding.editTextSearch.doAfterTextChanged {
            val searchedCategories = DataManager.searchInCategory(it.toString())
            adapter.setData(searchedCategories)
            binding.recycleRank.scrollToPosition(0)
        }

    }

    private fun setUp() {
        adapter = RankAdapter(DataManager.getAllCategory(), this)
        binding.recycleRank.adapter = adapter
    }

    override fun onClickItem(category: Category) {
        Toast.makeText(requireContext(), category.categoryName, Toast.LENGTH_SHORT).show()
    }
}