package com.kiko.costmanager.logic.ui.rank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kiko.costmanager.databinding.ItemRankBinding
import com.kiko.costmanager.logic.data.models.CityEntity
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter

class RankAdapter(private val list: List<CityEntity>, listener: RankInteractListener) :
    BaseViewsAdapter<CityEntity, ItemRankBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemRankBinding
        get() = ItemRankBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: CityEntity) {
        Glide.with(holder.itemView.context).load(item.image).into(
            holder.binding.imageCategory
        )
        holder.binding.textCategory.text = item.cityName
    }

}