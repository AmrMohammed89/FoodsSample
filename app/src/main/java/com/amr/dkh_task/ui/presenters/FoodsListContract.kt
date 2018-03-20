package com.amr.dkh_task.ui.presenters

import com.amr.dkh_task.data.net.model.ItemsItem
import com.amr.dkh_task.ui.BaseView

interface FoodsListContract {
    interface View : BaseView {
        fun setLoadingIndicatorVisible(loadingVisible: Boolean)

        fun showFoodList(foodsList: MutableList<ItemsItem>)

        fun showNoConnectivityError()
        fun showUnknownError()
    }

    interface Presenter : BasePresenter<View> {
        fun getFoodsList()
    }
}