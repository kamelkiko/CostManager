package com.kiko.costmanager.logic.ui.home


import com.denzcoskun.imageslider.models.SlideModel
import com.kiko.costmanager.logic.data.models.CityEntity


sealed class HomeItem {
    data class Banner(
        val list:List<SlideModel>,
        val type: HomeItemType = HomeItemType.BANNER
    ) : HomeItem()

    data class PopularCities(
        val data: List<CityEntity>,
        val type: HomeItemType = HomeItemType.POPULAR_CITIES
    ) : HomeItem()

    data class AverageSalariesCities(
        val data: List<CityEntity>,
        val type: HomeItemType = HomeItemType.AVERAGE_SALARIES_CITIES
    ) : HomeItem()

    data class AverageInternetCities(
        val data: List<CityEntity>,
        val type: HomeItemType = HomeItemType.AVERAGE_INTERNET_CITIES
    ) : HomeItem()

    data class CitiesHaveDataQuality(
        val data: List<CityEntity>,
        val type: HomeItemType = HomeItemType.CITY_HAS_DATA_QUALITY
    ) : HomeItem()

    data class JustForYou(
        val data: List<CityEntity>,
        val type: HomeItemType = HomeItemType.JUST_FOR_YOU
    ) : HomeItem()
}