package com.kiko.costmanager.logic.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentSearchBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.Base.HomeActivity
import com.kiko.costmanager.logic.ui.details.DetailsFragment
import com.kiko.costmanager.logic.ui.search.adapter.SearchAdapter
import com.kiko.costmanager.logic.ui.search.adapter.SearchInteractListener


class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchInteractListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
    private lateinit var adapter: SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    private fun addCallBack() {
        binding.recycleSearch.adapter = adapter
        binding.editTextSearch.doAfterTextChanged {
            val search = DataManager.search(it.toString())
            if (search.isNotEmpty()) {
                adapter.setData(search)
                binding.recycleSearch.scrollToPosition(0)
            } else adapter.setData(emptyList())
        }

    }

    private fun setup() {
        (activity as HomeActivity).bottomNavView(true)
        adapter = SearchAdapter(emptyList(), this)
    }

    override fun onClickItem(cityEntity: CityEntity) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, DetailsFragment.newInstance(cityEntity.id))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
        (activity as HomeActivity).bottomNavView(false)
    }

}