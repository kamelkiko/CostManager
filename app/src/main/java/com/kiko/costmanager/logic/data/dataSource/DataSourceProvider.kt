package com.kiko.costmanager.logic.data.dataSource

import com.kiko.costmanager.logic.data.models.CityEntity
import java.io.Serializable

interface DataSourceProvider : Serializable {
    fun getAllCities(): List<CityEntity>
}