package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopItemsAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    companion object {
        const val ENABLED_VALUE = 1
        const val DISABLED_VALUE = 0
        const val MAX_POOL_SIZE = 5
    }

    var shopItemOnLongClickListener: ((ShopItem) -> Unit)? = null
    var shopItemOnClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layour = when (viewType) {
            ENABLED_VALUE -> R.layout.item_shop_enabled
            DISABLED_VALUE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layour,
            parent,
            false
        )

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.nameTv.text = item.name
        holder.countTv.text = item.count.toString()

        holder.view.setOnClickListener {
            shopItemOnClickListener?.invoke(item)
        }

        holder.view
            .setOnLongClickListener {
            shopItemOnLongClickListener?.invoke(item)
            true
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            ENABLED_VALUE
        } else {
            DISABLED_VALUE
        }

    }

}