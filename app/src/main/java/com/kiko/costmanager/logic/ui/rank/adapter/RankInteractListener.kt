package com.kiko.costmanager.logic.ui.rank.adapter

import com.kiko.costmanager.logic.data.models.CityEntity

interface RankInteractListener {
    fun onClickItem(cityEntity: CityEntity)
}