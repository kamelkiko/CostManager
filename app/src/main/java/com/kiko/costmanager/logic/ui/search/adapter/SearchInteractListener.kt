package com.kiko.costmanager.logic.ui.search.adapter

import com.kiko.costmanager.logic.data.models.CityEntity

interface SearchInteractListener {
    fun onClickItem(cityEntity: CityEntity)
    fun onClickFavouriteLogo(cityEntity: CityEntity)
}