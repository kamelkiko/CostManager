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
            Category(2, "Fruits", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(3, "Foods", Constants.ImageUrl.FOODS),
            Category(4, "Service", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(5, "Clothes", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(6, "Transportations", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(7, "Cars", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(8, "RealEstates", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(9, "Salaries", Constants.ImageUrl.SALARIES),
            Category(10, "Internet", Constants.ImageUrl.RANDOM_IMAGE_URL),
        )
    }

    fun searchInCategory(categoryName: String) =
        getAllCategory().filter {
            it.categoryName.lowercase().contains(categoryName.lowercase())
        }.toList()
}