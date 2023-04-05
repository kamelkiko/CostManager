package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClothesPrices(
    val onePairOfJeansLevis50oneOrSimilar: Float?,
    val oneSummerDressInAChainStoreZaraHAndM: Float?,
    val onePairOfNikeRunningShoesMidRange: Float?,
    val onePairOfMenLeatherBusinessShoes: Float?,
) : Parcelable
