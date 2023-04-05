package com.kiko.costmanager.logic.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransportationsPrices(
    val oneWayTicketLocalTransport: Float?,
    val monthlyPassRegularPrice: Float?,
    val taxiStartNormalTariff: Float?,
    val taxi1kmNormalTariff: Float?,
    val taxi1hourWaitingNormalTariff: Float?,
    val gasolineOneLiter: Float?,
) : Parcelable
