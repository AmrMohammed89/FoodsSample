package com.amr.dkh_task.ui.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.amr.dkh_task.DKHApp
import com.amr.dkh_task.dagger.components.DaggerFragmentComponent
import com.amr.dkh_task.dagger.components.FragmentComponent
import com.amr.dkh_task.dagger.modules.FoodsListModule

abstract class BaseFragment : Fragment() {

    internal lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = DaggerFragmentComponent.builder()
                .foodsListModule(FoodsListModule())
                .appComponent((activity.application as DKHApp).buildComponent())
                .build()
    }


    fun showOrHideToolbarBack(show: Boolean) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }


    fun showSnack(parent: ViewGroup, messageResId: Int, length: Int,
                  actionLabelResId: Int? = null, action: ((View) -> Unit)? = null,
                  callback: ((Snackbar) -> Unit)? = null) {

        showSnack(parent, getString(messageResId), length, actionLabelResId?.let { getString(it) }, action, callback)
    }

    private fun showSnack(parent: ViewGroup, message: String, length: Int,
                          actionLabel: String? = null, action: ((View) -> Unit)? = null,
                          callback: ((Snackbar) -> Unit)? = null) {

        Snackbar.make(parent, message, length)
                .apply {
                    if (actionLabel != null) {
                        setAction(actionLabel, action)
                    }

                    callback?.invoke(this)
                }
                .show()
    }
}
