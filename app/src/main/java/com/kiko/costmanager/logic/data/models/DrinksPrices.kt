package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DrinksPrices(
    val cappuccinoRegularInRestaurants: Float?,
    val cokePepsiAThirdOfLiterBottleInRestaurants: Float?,
    val waterAThirdOfLiterBottleInRestaurants: Float?,
    val milkRegularOneLiter: Float?,
    val waterOneAndHalfLiterBottleAtTheMarket: Float?,
) : Parcelable
