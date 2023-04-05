package com.kiko.costmanager.logic.ui.rank.adapter

import com.kiko.costmanager.logic.data.models.Category

interface RankInteractListener {
    fun onClickItem(category: Category)
}