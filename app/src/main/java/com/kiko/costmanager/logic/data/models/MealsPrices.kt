package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealsPrices(
    val mealInexpensiveRestaurant: Float?,
    val mealFor2PeopleMidRangeRestaurant: Float?,
    val mealAtMcDonaldSOrEquivalent: Float?,
) : Parcelable