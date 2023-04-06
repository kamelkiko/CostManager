package com.kiko.costmanager.logic.ui.seemore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemSearchBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.setImageUrl

class SeeMoreAdapter(list: List<CityEntity>, private val listener: SeeMoreInteractListener) :
    BaseViewsAdapter<CityEntity, ItemSearchBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemSearchBinding
        get() = ItemSearchBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.apply {
            imageSearch.setImageUrl(item.image)
            textCountryName.text = item.country
            textCityName.text = item.cityName
        }
        holder.binding.root.setOnClickListener {
            listener.onClickItem(item)
        }
    }
}