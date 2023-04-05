package com.kiko.costmanager.logic.ui.rank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kiko.costmanager.databinding.ItemRankBinding
import com.kiko.costmanager.logic.data.models.Category
import com.kiko.costmanager.logic.ui.Base.BaseViewsAdapter

class RankAdapter(private val list: List<Category>, private val listener: RankInteractListener) :
    BaseViewsAdapter<Category, ItemRankBinding>(list) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemRankBinding
        get() = ItemRankBinding::inflate

    override fun bindItems(holder: BaseViewHolder, position: Int, item: Category) {
        Glide.with(holder.itemView.context).load(item.categoryImage).into(
            holder.binding.imageCategory
        )
        holder.binding.textCategory.text = item.categoryName

        holder.binding.root.setOnClickListener {
            listener.onClickItem(item)
        }
    }

}