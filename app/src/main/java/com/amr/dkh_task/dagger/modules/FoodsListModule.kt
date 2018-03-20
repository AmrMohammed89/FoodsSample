package com.amr.dkh_task.dagger.modules

import com.amr.dkh_task.SchedulerContract
import com.amr.dkh_task.dagger.ActivityScope
import com.amr.dkh_task.data.net.ApiController
import com.amr.dkh_task.interactor.FoodsListInteractor
import com.amr.dkh_task.ui.presenters.FoodsListPresenter
import dagger.Module
import dagger.Provides

@Module
class FoodsListModule {


    @Provides
    @ActivityScope
    fun providesFoodsListInteractor(apiController: ApiController,
                                    schedulerContract: SchedulerContract)
            : FoodsListInteractor {
        return FoodsListInteractor(apiController, schedulerContract)
    }

    @Provides
    @ActivityScope
    fun providesFoodsListPresenter(foodsListInteractor: FoodsListInteractor)
            : FoodsListPresenter {
        return FoodsListPresenter(foodsListInteractor)
    }
}