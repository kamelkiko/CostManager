package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServicesPrices(
    val basicElectricityHeatingCoolingWaterGarbageFor85m2Apartment: Float?,
    val oneMinOfPrepaidMobileTariffLocalNoDiscountsOrPlans: Float?,
    val internet60MbpsOrMoreUnlimitedDataCableAdsl: Float?,
    val fitnessClubMonthlyFeeForOneAdult: Float?,
    val tennisCourtRentOneHourOnWeekend: Float?,
    val cinemaInternationalReleaseOneSeat: Float?,
    val preschoolOrKindergartenFullDayPrivateMonthlyForOneChild: Float?,
    val internationalPrimarySchoolYearlyForOneChild: Float?,
) : Parcelable
