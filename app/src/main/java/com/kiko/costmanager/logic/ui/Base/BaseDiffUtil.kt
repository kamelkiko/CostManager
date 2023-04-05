package com.kiko.costmanager.logic.ui.Base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val checkItemsTheSame: (oldItem: T, newItemT: T) -> Boolean,
    private val checkContentsTheSame: (oldItem: T, newItemT: T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])

}