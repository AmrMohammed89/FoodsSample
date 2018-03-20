package com.amr.dkh_task.dagger.components

import com.amr.dkh_task.dagger.ActivityScope
import com.amr.dkh_task.dagger.modules.FoodsListModule
import com.amr.dkh_task.ui.fragments.FoodsListFragment
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(FoodsListModule::class), dependencies = arrayOf(AppComponent::class))
interface FragmentComponent {

    fun inject(foodsListFragment: FoodsListFragment)
}
