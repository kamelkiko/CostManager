package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RealEstatesPrices(
    val apartmentOneBedroomInCityCentre: Float?,
    val apartmentOneBedroomOutsideOfCentre: Float?,
    val apartment3BedroomsInCityCentre: Float?,
    val apartment3BedroomsOutsideOfCentre: Float?,
    val pricePerSquareMeterToBuyApartmentInCityCentre: Float?,
    val pricePerSquareMeterToBuyApartmentOutsideOfCentre: Float?,
) : Parcelable
