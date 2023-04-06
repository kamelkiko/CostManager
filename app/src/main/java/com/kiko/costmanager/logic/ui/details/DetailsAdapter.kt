package com.kiko.costmanager.logic.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemDetailsBinding
import com.kiko.costmanager.logic.data.models.Prices
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.setImageUrl

class DetailsAdapter(list: List<Prices>) :
    BaseViewsAdapter<Prices, ItemDetailsBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemDetailsBinding
        get() = ItemDetailsBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun bindItems(holder: BaseViewHolder, position: Int, item: Prices) {
        holder.binding.apply {
            imageCategory.setImageUrl(item.image)
            textViewAverageName.text = item.name
            textViewAverageNumber.text = item.price.toString()
        }
    }
}