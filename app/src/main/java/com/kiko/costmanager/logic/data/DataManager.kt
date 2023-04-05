package com.kiko.costmanager.logic.data

import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.util.Constants

object DataManager {
    private val _cities = mutableListOf<CityEntity>()
    private val cities get() = _cities

    private val _categories = mutableListOf<Category>()
    private val categories get() = _categories
    fun addCity(cityEntity: CityEntity) {
        _cities.add(cityEntity)
    }

    fun getAllCitiesData() = cities

    fun getAllCategory(): List<Category> {
        val list = listOf(
            Category(0, "Meals", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(1, "Drinks", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(2, "FruitAndVegetables", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(3, "Food", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(4, "Service", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(5, "Clothes", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(6, "Transportations", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(7, "Cars", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(8, "RealEstates", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(9, "Salary", Constants.ImageUrl.RANDOM_IMAGE_URL),
            Category(10, "Internet", Constants.ImageUrl.RANDOM_IMAGE_URL),
        )
        _categories.addAll(list)
        return categories
    }

    fun searchInCategory(categoryName: String) =
        categories.filter {
            it.categoryName.lowercase().contains(categoryName.lowercase())
        }.toList()
}