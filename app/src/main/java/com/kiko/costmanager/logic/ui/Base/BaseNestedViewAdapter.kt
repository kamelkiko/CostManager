package com.kiko.costmanager.logic.ui.Base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface BaseInteractionListener
abstract class BaseNestedViewAdapter<T, VB : ViewBinding>(var items: List<T>) :
    RecyclerView.Adapter<BaseNestedViewAdapter.ItemViewHolder<VB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent, viewType)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<VB>, position: Int) {
        val item = items[position]
        bind(holder.binding, item)
    }

    override fun getItemCount(): Int = items.size

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB

    abstract fun bind(binding: VB, item: T)

    class ItemViewHolder<VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)
}