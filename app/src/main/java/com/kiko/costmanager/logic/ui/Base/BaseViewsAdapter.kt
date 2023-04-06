package com.kiko.costmanager.logic.ui.Base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewsAdapter<T, VB : ViewBinding>(private var list: List<T>) :
    RecyclerView.Adapter<BaseViewsAdapter<T, VB>.BaseViewHolder>() {
    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder( binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = list[position]
        bindItems(holder, position, currentItem)
    }

    override fun getItemCount() = list.size ?: 0

    open fun setData(newList: List<T>) {
        val diffResult = DiffUtil.calculateDiff(
            BaseDiffUtil(
                list,
                newList,
                ::areItemTheSame,
                ::areContentTheSame
            )
        )
        list = newList
        diffResult.dispatchUpdatesTo(this@BaseViewsAdapter)
    }

    open fun areItemTheSame(oldItem: T, newItem: T) = oldItem?.equals(newItem) == true
    fun hi() {}

    open fun areContentTheSame(oldItem: T, newItem: T) = oldItem?.equals(newItem) == true

    abstract fun bindItems(holder: BaseViewHolder, position: Int, item: T)

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}