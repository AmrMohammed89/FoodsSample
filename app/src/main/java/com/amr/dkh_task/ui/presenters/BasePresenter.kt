package com.amr.dkh_task.ui.presenters

import com.amr.dkh_task.ui.BaseView

interface BasePresenter<in V : BaseView> {
    fun detachView(view: V)
    fun attachView(view: V)

    fun destroy()
}