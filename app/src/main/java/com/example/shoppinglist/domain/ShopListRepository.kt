package com.example.shoppinglist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun changeShopItem(shopItem: ShopItem)

    fun deleteSHopItem(shopItem: ShopItem)

    fun getShopItemById(id: Int): ShopItem

    fun getItemsList(): List<ShopItem>
}