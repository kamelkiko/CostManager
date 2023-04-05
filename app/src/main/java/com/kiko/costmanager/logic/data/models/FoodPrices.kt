package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodPrices(
    val loafOfFreshWhiteBread500g: Float?,
    val riceWhite1kg: Float?,
    val eggsRegular12: Float?,
    val localCheese1kg: Float?,
    val chickenFillets1kg: Float?,
    val beefRound1kgOrEquivalentBackLegRedMeat: Float?,
) : Parcelable
