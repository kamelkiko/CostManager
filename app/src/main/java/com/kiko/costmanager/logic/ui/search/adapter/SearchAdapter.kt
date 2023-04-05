package com.kiko.costmanager.logic.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kiko.costmanager.R
import com.kiko.costmanager.databinding.ItemSearchBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter
import com.kiko.costmanager.logic.util.setImageUrl

class SearchAdapter(
    list: List<CityEntity>,
    private val listener: SearchInteractListener
) :
    BaseViewsAdapter<CityEntity, ItemSearchBinding>(
        list
    ) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemSearchBinding
        get() = ItemSearchBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        holder.binding.root.setOnClickListener {
            listener.onClickItem(item)
        }
        holder.binding.imgFavourite.setOnClickListener {
            listener.onClickFavouriteLogo(item)
            holder.binding.imgFavourite.setImageResource(R.drawable.ic_heart)
        }
        holder.binding.apply {
            textCityName.text = item.cityName
            textCountryName.text = item.country
            imageSearch.setImageUrl(item.image)
            if (!item.isFavourite)
                imgFavourite.setImageResource(R.drawable.ic_heart_outlined)
            else
                imgFavourite.setImageResource(R.drawable.ic_heart)
        }
    }
}