package com.kiko.costmanager.logic.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.databinding.ItemPopularCityBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.setImageUrl

class PopularCitiesAdapter(
    items: List<CityEntity>,
    private val listener: HomeInteractionListener
) : BaseViewsAdapter<CityEntity, ItemPopularCityBinding>(items) {

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemPopularCityBinding
        get() = ItemPopularCityBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.apply {
            imageCity.setImageUrl(item.image)
            textCityName.text = item.cityName
            textCountryName.text = item.country
            root.setOnClickListener { listener.onClickItem(item) }
        }
    }
}