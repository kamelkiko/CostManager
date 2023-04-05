package com.kiko.costmanager.logic.data.dataSource

import android.content.Context
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvDataSource(private val parser: CsvParser, private val context: Context) :
    CostOfLivingDataSource {
    override fun getAllCitiesData(): List<CityEntity> {
        val list = mutableListOf<CityEntity>()
        getCsvFile().forEachLine {
            val city = parser.parseLine(it)
            list.add(city)
        }
        return list.take(10)
    }

    private fun getCsvFile(): BufferedReader {
        val inputStream = context.assets.open(FILE_NAME)
        return BufferedReader(InputStreamReader(inputStream))
    }

    companion object {
        private const val FILE_NAME = "costOfLiving.csv"
    }
}