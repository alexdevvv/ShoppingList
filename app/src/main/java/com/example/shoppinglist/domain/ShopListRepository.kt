package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun changeShopItem(shopItem: ShopItem)

    fun deleteSHopItem(shopItem: ShopItem)

    fun getShopItemById(id: Int): ShopItem

    fun getShopItemsList(): LiveData<List<ShopItem>>
}