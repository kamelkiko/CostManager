package com.kiko.costmanager.logic.data.dataSource

import com.kiko.costmanager.logic.data.models.CityEntity

class DataSourceImpl(private val csvDataSource: CsvDataSource) : DataSourceProvider {
    override fun getAllCities(): List<CityEntity> {
        return csvDataSource.getAllCitiesData()
    }
}