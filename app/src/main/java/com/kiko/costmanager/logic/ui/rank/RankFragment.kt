package com.kiko.costmanager.logic.ui.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.airbnb.lottie.LottieDrawable
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentRankBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
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
        addCallBack()
    }

    private fun addCallBack() {
        binding.recycleRank.adapter = adapter
        binding.editTextSearch.doAfterTextChanged {
            val searchedCategories = DataManager.searchInCategory(it.toString())
            if (searchedCategories.isNotEmpty()) {
                hideAnimation()
                adapter.setData(searchedCategories)
                binding.recycleRank.scrollToPosition(0)
            } else
                showAnimation()
        }

    }

    private fun setUp() {
        (activity as HomeActivity).bottomNavView(true)
        adapter = RankAdapter(DataManager.getAllCategory(), this)
    }

    override fun onClickItem(category: Category) {
        Toast.makeText(requireContext(), category.categoryName, Toast.LENGTH_SHORT).show()
    }

    private fun showAnimation() {
        binding.apply {
            lottie.visibility = View.VISIBLE
            lottie.setAnimation(R.raw.search)
            lottie.repeatCount = LottieDrawable.INFINITE
            lottie.playAnimation()
            binding.recycleRank.visibility = View.GONE
        }
    }

    private fun hideAnimation() {
        binding.apply {
            recycleRank.visibility = View.VISIBLE
            lottie.visibility = View.GONE
        }
    }
}