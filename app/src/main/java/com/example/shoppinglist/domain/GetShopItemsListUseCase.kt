package com.example.shoppinglist.domain

class GetShopItemsListUseCase(private val shopListRepository: ShopListRepository) {

    fun getItemsList(): List<ShopItem> = shopListRepository.getItemsList()

}