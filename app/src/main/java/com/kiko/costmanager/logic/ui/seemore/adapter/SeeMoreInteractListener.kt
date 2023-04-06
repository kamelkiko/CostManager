package com.kiko.costmanager.logic.ui.seemore.adapter

import com.kiko.costmanager.logic.data.models.CityEntity

interface SeeMoreInteractListener {
    fun onClickItem(cityEntity: CityEntity)
}