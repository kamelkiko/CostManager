package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarsPrices(
    val volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar: Float?,
    val toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar: Float?,
) : Parcelable
