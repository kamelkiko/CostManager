package com.kiko.costmanager.logic.ui.home.adapter

import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.home.HomeItemType

interface HomeInteractionListener {
    fun onClickItem(cityEntity: CityEntity)
    fun onClickSeeMore(type: HomeItemType)
}