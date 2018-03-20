package com.amr.dkh_task.dagger.modules

import android.content.Context
import com.amr.dkh_task.DKHApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: DKHApp) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesApplication(): DKHApp = application

}