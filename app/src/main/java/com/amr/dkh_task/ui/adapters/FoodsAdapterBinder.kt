package com.amr.dkh_task.ui.adapters

import com.amr.dkh_task.data.net.model.ItemsItem

interface FoodsAdapterBinder {
    fun getFoodsList(): MutableList<ItemsItem>
    fun showFoodList(foodsList: MutableList<ItemsItem>)
    fun openFoodDetails(food: ItemsItem)
}