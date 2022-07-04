package com.example.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteSHopITem(shopItem: ShopItem){
        shopListRepository.deleteSHopITem(shopItem)
    }
}