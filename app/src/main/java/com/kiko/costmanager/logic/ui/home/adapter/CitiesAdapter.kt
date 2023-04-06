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
            imageCityCard.setImageUrl("https://images.pexels.com/photos/1486222/pexels-photo-1486222.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            textCityCard.text = item.cityName
            textCountryCard.text = item.country
            root.setOnClickListener { listener.onClickItem(item) }
        }
    }

}