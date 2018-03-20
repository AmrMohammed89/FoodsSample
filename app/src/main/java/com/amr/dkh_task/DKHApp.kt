package com.amr.dkh_task

import android.app.Application
import com.amr.dkh_task.dagger.components.AppComponent
import com.amr.dkh_task.dagger.components.DaggerAppComponent
import com.amr.dkh_task.dagger.modules.ApiModule
import com.amr.dkh_task.dagger.modules.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration


class DKHApp : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder().name("dkh.realm").build()
        Realm.setDefaultConfiguration(config)
        appComponent = buildComponent()
    }


    fun buildComponent(): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .build()

}