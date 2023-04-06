package com.kiko.costmanager.logic.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemCityBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.setImageUrl

class CitiesAdapter(list: List<CityEntity>, private val listener: HomeInteractionListener) :
    BaseViewsAdapter<CityEntity, ItemCityBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCityBinding
        get() = ItemCityBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.apply {
            textCity.text = item.cityName
            textCountry.text = item.country
            imageCity.setImageUrl(item.image)
            root.setOnClickListener { listener.onClickItem(item) }
        }
    }

}