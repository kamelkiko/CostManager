package com.kiko.costmanager.logic.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemDetailsBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.Constants
import com.kiko.costmanager.logic.util.setImageUrl

class DetailsAdapter(list: List<CityEntity>) :
    BaseViewsAdapter<CityEntity, ItemDetailsBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemDetailsBinding
        get() = ItemDetailsBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.apply {
            imageCategory.setImageUrl(Constants.ImageUrl.SALARIES)
            textViewAverageName.text = item.cityName
            textViewAverageNumber.text = item.averageMonthlyNetSalaryAfterTax.toString()
        }
    }
}