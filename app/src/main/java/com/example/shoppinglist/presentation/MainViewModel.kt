package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShoppingListRepositoryImpl
import com.example.shoppinglist.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShoppingListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemsListUseCase = GetShopItemsListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val changeShopItemUseCase = ChangeShopItemUseCase(repository)

    val shopList = getShopItemsListUseCase.getShopItemsList()

    fun deleteShoItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteSHopITem(shopItem)

    }
    fun changeShopItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        changeShopItemUseCase.changeShopItem(newShopItem)
    }
}