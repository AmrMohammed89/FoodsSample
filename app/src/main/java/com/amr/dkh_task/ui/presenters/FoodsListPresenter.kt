package com.amr.dkh_task.ui.presenters

import com.amr.dkh_task.data.net.model.ItemsItem
import com.amr.dkh_task.exception.NoNetworkException
import com.amr.dkh_task.interactor.FoodsListInteractor
import com.amr.dkh_task.interactor.FoodsListInteractorContract

class FoodsListPresenter(private val foodsListInteractor: FoodsListInteractor)
    : FoodsListContract.Presenter, FoodsListInteractorContract.OnListLoadedListener {


    var view: FoodsListContract.View? = null

    override fun detachView(view: FoodsListContract.View) {
        this.view = null
    }

    override fun attachView(view: FoodsListContract.View) {
        this.view = view
    }

    override fun destroy() {
        foodsListInteractor.destroy()
        foodsListInteractor.realm
    }

    override fun getFoodsList() {
        showLoading(true)
        foodsListInteractor.getFoodsList(this)
    }

    override fun onError(throwable: Throwable) {
        showError(throwable)
    }

    override fun onSuccess(items: MutableList<ItemsItem>) {
        showFoodList(items)
    }


    private fun showFoodList(items: MutableList<ItemsItem>) {
        showLoading(false)
        view?.showFoodList(items)
    }


    private fun showError(throwable: Throwable) {
        showLoading(false)
        when (throwable) {
            NoNetworkException -> view?.showNoConnectivityError()
            else -> {
                view?.showUnknownError()
            }
        }
    }

    private fun showLoading(isVisible: Boolean) {
        view?.setLoadingIndicatorVisible(isVisible)
    }

}
