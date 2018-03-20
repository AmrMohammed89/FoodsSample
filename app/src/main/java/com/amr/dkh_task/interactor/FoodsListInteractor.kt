package com.amr.dkh_task.interactor

import addToCompositeDisposable
import com.amr.dkh_task.SchedulerContract
import com.amr.dkh_task.data.net.ApiController
import com.amr.dkh_task.data.net.model.ItemsItem
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import uiSubscribe

class FoodsListInteractor(private val apiController: ApiController,
                          private val schedulerContract: SchedulerContract) : FoodsListInteractorContract {


    val realm: Realm = Realm.getDefaultInstance()
    private val compositeDisposable = CompositeDisposable()


    override fun getFoodsList(onListLoadedListener: FoodsListInteractorContract.OnListLoadedListener) {

        realm.where(ItemsItem::class.java)?.findAllAsync()?.asFlowable()
                ?.subscribe({
                    onListLoadedListener.onSuccess(it)
                }, {
                    onListLoadedListener.onError(it)
                }).let { callApi(onListLoadedListener) }

    }


    private fun callApi(onListLoadedListener: FoodsListInteractorContract.OnListLoadedListener) {
        apiController.getFoodList()
                .uiSubscribe(schedulerContract)
                .doOnError {
                }
                .subscribe({ list ->
                    realm.executeTransaction({
                        realm.copyToRealmOrUpdate(list.items)
                    })
                    onListLoadedListener.onSuccess(list.items)

                }, { e ->
                    onListLoadedListener.onError(e)
                })
                .addToCompositeDisposable(compositeDisposable)
    }


    override fun destroy() {
        realm.close()
        compositeDisposable.clear()
    }
}