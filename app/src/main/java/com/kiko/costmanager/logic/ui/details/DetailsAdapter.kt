package com.kiko.costmanager.logic.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemDetailsBinding
import com.kiko.costmanager.logic.data.DataManager
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.Constants
import com.kiko.costmanager.logic.util.setImageUrl

class DetailsAdapter(list: List<CityEntity>) :
    BaseViewsAdapter<CityEntity, ItemDetailsBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemDetailsBinding
        get() = ItemDetailsBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.apply {
            imageCategory.setImageUrl(Constants.ImageUrl.SALARIES)
            textViewAverageName.text = DataManager.getAllCategory()[9].categoryName
            textViewAverageNumber.text =
                (((item.mealsPrices.mealAtMcDonaldSOrEquivalent?.plus(item.mealsPrices.mealFor2PeopleMidRangeRestaurant!!)
                    ?.plus(
                        item.mealsPrices
                            .mealInexpensiveRestaurant!!
                    ))?.div(3))).toString()
        }
    }
}