package com.kiko.costmanager.logic.data

import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.util.Constants

object DataManager {
    private val cities = mutableListOf<CityEntity>()
    fun addCity(cityEntity: CityEntity) {
        cities.add(cityEntity)
    }

    fun clearCity() {
        cities.clear()
    }

    fun getAllCitiesData() = cities

    fun getAllCategory(): List<Category> {
        return listOf(
            Category(0, "Meals", Constants.ImageUrl.MEALS),
            Category(1, "Drinks", Constants.ImageUrl.DRINKS),
            Category(2, "Fruits", Constants.ImageUrl.FRUITS),
            Category(3, "Foods", Constants.ImageUrl.FOODS),
            Category(4, "Service", Constants.ImageUrl.SERVICES),
            Category(5, "Clothes", Constants.ImageUrl.CLOTHES),
            Category(6, "Transportations", Constants.ImageUrl.TRANSPORTATIONS),
            Category(7, "Cars", Constants.ImageUrl.CARS),
            Category(8, "RealEstates", Constants.ImageUrl.REAL_STATES),
            Category(9, "Salaries", Constants.ImageUrl.SALARIES),
            Category(10, "Internet", Constants.ImageUrl.INTERNET),
        )
    }

    fun searchInCategory(categoryName: String) =
        getAllCategory().filter {
            it.categoryName.lowercase().contains(categoryName.lowercase())
        }.toList()

    fun search(name: String) =
        getAllCitiesData().filter {
            it.cityName.lowercase().contains(name.lowercase()) ||
                    it.country.lowercase().contains(name.lowercase())
        }
}