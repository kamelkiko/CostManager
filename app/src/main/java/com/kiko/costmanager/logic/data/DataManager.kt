package com.kiko.costmanager.logic.data

import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.interactors.Interacts
import com.kiko.costmanager.logic.util.Constants

object DataManager {
    private val cities = mutableListOf<CityEntity>()
    fun addCity(cityEntity: CityEntity) {
        cities.add(cityEntity)
    }

    fun clearCity() {
        cities.clear()
    }

    fun getAllInteracts() = Interacts(this)

    fun getAllCitiesData() = cities.toList()

    fun getAllCategory() =
        listOf(
            Category(0, Constants.MEALS, Constants.ImageUrl.MEALS),
            Category(1, Constants.DRINKS, Constants.ImageUrl.DRINKS),
            Category(2, Constants.FRUITS, Constants.ImageUrl.FRUITS),
            Category(3, Constants.FOODS, Constants.ImageUrl.FOODS),
            Category(4, Constants.SERVICES, Constants.ImageUrl.SERVICES),
            Category(5, Constants.CLOTHES, Constants.ImageUrl.CLOTHES),
            Category(6, Constants.TRANS, Constants.ImageUrl.TRANSPORTATIONS),
            Category(7, Constants.CARS, Constants.ImageUrl.CARS),
            Category(8, Constants.STATES, Constants.ImageUrl.REAL_STATES),
            Category(9, Constants.SALARIES, Constants.ImageUrl.SALARIES),
            Category(10, Constants.INTERNET, Constants.ImageUrl.INTERNET),
        )


    fun searchInCategory(categoryName: String) =
        getAllCategory().filter {
            it.categoryName.lowercase().contains(categoryName.lowercase())
        }.toList()

    fun search(name: String) =
        getAllCitiesData().filter {
            it.cityName.lowercase().contains(name.lowercase()) ||
                    it.country.lowercase().contains(name.lowercase())
        }

    fun getCityById(id: Int) = getAllCitiesData().filter { it.id == id }

}