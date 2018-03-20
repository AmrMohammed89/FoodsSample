package com.amr.dkh_task.interactor

import com.amr.dkh_task.data.net.model.ItemsItem

interface FoodsListInteractorContract {
    interface OnListLoadedListener {
        fun onError(throwable: Throwable)

        fun onSuccess(items: MutableList<ItemsItem>)
    }

    fun getFoodsList(onListLoadedListener: OnListLoadedListener)

    fun destroy()
}