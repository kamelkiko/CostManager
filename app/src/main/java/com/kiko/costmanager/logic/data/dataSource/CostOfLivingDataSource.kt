package com.kiko.costmanager.logic.data.dataSource

import com.kiko.costmanager.logic.data.models.CityEntity


interface CostOfLivingDataSource {
    fun getAllCitiesData(): List<CityEntity>
}

