package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopItemsListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItemsList(): LiveData<List<ShopItem>> = shopListRepository.getShopItemsList()

}