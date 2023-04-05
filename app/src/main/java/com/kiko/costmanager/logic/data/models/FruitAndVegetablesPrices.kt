package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FruitAndVegetablesPrices(
    val apples1kg: Float?,
    val banana1kg: Float?,
    val oranges1kg: Float?,
    val tomato1kg: Float?,
    val potato1kg: Float?,
    val onion1kg: Float?,
    val lettuceOneHead: Float?,
) : Parcelable
