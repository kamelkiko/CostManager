package com.kiko.costmanager.logic.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kiko.costmanager.databinding.*
import com.kiko.costmanager.logic.ui.Base.BaseNestedViewAdapter
import com.kiko.costmanager.logic.ui.home.HomeItem

class HomeAdapter(list: List<HomeItem>, private val listener: HomeInteractionListener) :
    BaseNestedViewAdapter<HomeItem, ViewBinding>(list) {
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            BANNER -> ItemBannerBinding.inflate(inflater, parent, false)
            AVERAGE_SALARIES_CITIES -> ListSalaryBinding.inflate(inflater, parent, false)
            POPULAR_CITIES -> ListPopularCityBinding.inflate(inflater, parent, false)
            AVERAGE_INTERNET_CITIES -> ListInternetBinding.inflate(inflater, parent, false)
            CITY_HAS_DATA_QUALITY -> ListDataQualityBinding.inflate(inflater, parent, false)
            JUST_FOR_YOU -> ListJustForYouBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException(UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeItem.Banner -> BANNER
            is HomeItem.AverageSalariesCities -> AVERAGE_SALARIES_CITIES
            is HomeItem.PopularCities -> POPULAR_CITIES
            is HomeItem.AverageInternetCities -> AVERAGE_INTERNET_CITIES
            is HomeItem.CitiesHaveDataQuality -> CITY_HAS_DATA_QUALITY
            is HomeItem.JustForYou -> JUST_FOR_YOU
        }
    }

    override fun bind(binding: ViewBinding, item: HomeItem) {
        when (binding) {
            is ItemBannerBinding -> bindBanner(binding, item as HomeItem.Banner)
            is ListSalaryBinding -> bindAverageSalary(
                binding,
                item as HomeItem.AverageSalariesCities
            )
            is ListPopularCityBinding -> bindPopularCity(
                binding,
                item as HomeItem.PopularCities
            )
            is ListInternetBinding -> bindAverageInternet(
                binding,
                item as HomeItem.AverageInternetCities
            )
            is ListDataQualityBinding -> bindDataQuality(
                binding,
                item as HomeItem.CitiesHaveDataQuality
            )
            is ListJustForYouBinding -> bindJustForYou(binding, item as HomeItem.JustForYou)
        }
    }

    private fun bindBanner(binding: ItemBannerBinding, item: HomeItem.Banner) {
        binding.apply {
            imageSlider.setImageList(item.list)
        }
    }

    private fun bindAverageSalary(
        binding: ListSalaryBinding,
        item: HomeItem.AverageSalariesCities
    ) {
        binding.apply {
            recyclerSalary.adapter = PopularCitiesAdapter(item.data, listener)
            seeMore.setOnClickListener { listener.onClickSeeMore(item.type) }
        }
    }

    private fun bindPopularCity(binding: ListPopularCityBinding, item: HomeItem.PopularCities) {
        binding.apply {
            recyclerPopular.adapter = PopularCitiesAdapter(item.data, listener)
        }
    }

    private fun bindAverageInternet(
        binding: ListInternetBinding,
        item: HomeItem.AverageInternetCities
    ) {
        binding.apply {
            recyclerInternet.adapter = PopularCitiesAdapter(item.data, listener)
            seeMore.setOnClickListener { listener.onClickSeeMore(item.type) }
        }
    }

    private fun bindDataQuality(
        binding: ListDataQualityBinding,
        item: HomeItem.CitiesHaveDataQuality
    ) {
        binding.apply {
            recyclerDataQuality.adapter = PopularCitiesAdapter(item.data, listener)
            seeMore.setOnClickListener { listener.onClickSeeMore(item.type) }
        }
    }

    private fun bindJustForYou(binding: ListJustForYouBinding, item: HomeItem.JustForYou) {
        binding.recyclerJustForYou.adapter = PopularCitiesAdapter(item.data, listener)
        binding.seeMore.setOnClickListener { listener.onClickSeeMore(item.type) }
    }

    companion object {
        const val BANNER = 0
        const val AVERAGE_SALARIES_CITIES = 1
        const val POPULAR_CITIES = 2
        const val AVERAGE_INTERNET_CITIES = 3
        const val CITY_HAS_DATA_QUALITY = 4
        const val JUST_FOR_YOU = 5
        const val UNKNOWN_HOME_ITEM_TYPE = "Unknown home item type"
        const val PARIS =
            "https://images.pexels.com/photos/2082103/pexels-photo-2082103.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        const val ROME =
            "https://images.pexels.com/photos/2064827/pexels-photo-2064827.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        const val PORTO =
            "https://images.pexels.com/photos/4110901/pexels-photo-4110901.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        const val CAIRO =
            "https://images.pexels.com/photos/5609738/pexels-photo-5609738.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        const val NEW_YORK =
            "https://images.pexels.com/photos/1486222/pexels-photo-1486222.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
    }
}