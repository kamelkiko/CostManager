package com.kiko.costmanager.logic.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.airbnb.lottie.LottieDrawable
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentSearchBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.search.adapter.SearchAdapter
import com.kiko.costmanager.logic.ui.search.adapter.SearchInteractListener


class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    private lateinit var adapter: SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        showAnimation()
        addCallBack()
    }

    private fun addCallBack() {
        binding.editTextSearch.doAfterTextChanged {
            if (it.isNullOrEmpty())
                showAnimation()
            val search = DataManager.search(it.toString())
            if (search.isNotEmpty()) {
                adapter.setData(search)
                binding.recycleSearch.scrollToPosition(0)
                hideAnimation()
            }
        }

    }

    private fun setup() {
        (activity as HomeActivity).bottomNavView(true)
        adapter = SearchAdapter(emptyList(), this)
        binding.recycleSearch.adapter = adapter
    }

    override fun onClickItem(cityEntity: CityEntity) {
        Toast.makeText(requireContext(), cityEntity.cityName, Toast.LENGTH_SHORT).show()
    }

    override fun onClickFavouriteLogo(cityEntity: CityEntity) {
        DataManager.addFavouriteCity(cityEntity)
        Toast.makeText(requireContext(), "Added!", Toast.LENGTH_SHORT).show()
    }

    private fun showAnimation() {
        binding.apply {
            lottie.visibility = View.VISIBLE
            lottie.setAnimation(R.raw.search)
            lottie.repeatCount = LottieDrawable.INFINITE
            lottie.playAnimation()
            binding.recycleSearch.visibility = View.GONE
        }
    }

    private fun hideAnimation() {
        binding.apply {
            recycleSearch.visibility = View.VISIBLE
            lottie.visibility = View.GONE
        }
    }
}