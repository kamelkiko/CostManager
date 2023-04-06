package com.kiko.costmanager.logic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.FragmentHomeBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseFragment
import com.kiko.costmanager.logic.ui.details.DetailsFragment
import com.kiko.costmanager.logic.ui.home.adapter.HomeAdapter
import com.kiko.costmanager.logic.ui.home.adapter.HomeInteractionListener
import com.kiko.costmanager.logic.util.PrefsUtil


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteractionListener {
    private lateinit var homeItems: MutableList<HomeItem>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindHomeItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        PrefsUtil.isUserLoggedOut = false
    }

    private fun setup() {
        binding.recyclerHome.adapter = HomeAdapter(homeItems, this)
    }

    private fun bindHomeItems() {
        val imageSliders = ArrayList<SlideModel>()
        imageSliders.add(SlideModel(HomeAdapter.PARIS, "Paris", ScaleTypes.CENTER_CROP))
        imageSliders.add(SlideModel(HomeAdapter.ROME, "Rome", ScaleTypes.CENTER_CROP))
        imageSliders.add(SlideModel(HomeAdapter.PORTO, "Porto", ScaleTypes.CENTER_CROP))
        imageSliders.add(SlideModel(HomeAdapter.CAIRO, "Cairo", ScaleTypes.CENTER_CROP))
        imageSliders.add(SlideModel(HomeAdapter.NEW_YORK, "NewYork", ScaleTypes.CENTER_CROP))

        homeItems = mutableListOf()
        homeItems.add(HomeItem.Banner(imageSliders))
        homeItems.add(HomeItem.PopularCities(DataManager.getAllCitiesData().take(5)))
        homeItems.add(HomeItem.AverageSalariesCities(DataManager.getAllCitiesData().take(5)))
        homeItems.add(HomeItem.AverageInternetCities(DataManager.getAllCitiesData().take(5)))
        homeItems.add(HomeItem.CitiesHaveDataQuality(DataManager.getAllCitiesData().take(5)))
        homeItems.add(HomeItem.JustForYou(DataManager.getAllCitiesData().take(5)))
    }

    override fun onClickItem(cityEntity: CityEntity) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, DetailsFragment.newInstance(cityEntity.id))
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }

    override fun onClickSeeMore(type: HomeItemType) {
        when (type) {
            HomeItemType.AVERAGE_SALARIES_CITIES -> {}
            HomeItemType.AVERAGE_INTERNET_CITIES -> {}
            HomeItemType.CITY_HAS_DATA_QUALITY -> {}

            HomeItemType.JUST_FOR_YOU -> {}

            else -> {}
        }
    }
}