package com.amr.dkh_task.dagger.components

import com.amr.dkh_task.SchedulerContract
import com.amr.dkh_task.dagger.modules.ApiModule
import com.amr.dkh_task.dagger.modules.AppModule
import com.amr.dkh_task.data.net.ApiController
import com.amr.dkh_task.interactor.FoodsListInteractor
import com.amr.dkh_task.ui.presenters.FoodsListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {
    fun getApiController(): ApiController

    fun getSchedulerContract(): SchedulerContract

    fun inject(foodsListPresenter: FoodsListPresenter)

    fun inject(foodsListInteractor: FoodsListInteractor)

}
