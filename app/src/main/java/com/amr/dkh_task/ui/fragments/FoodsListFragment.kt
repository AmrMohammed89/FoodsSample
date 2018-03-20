package com.amr.dkh_task.ui.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amr.dkh_task.R
import com.amr.dkh_task.data.net.model.ItemsItem
import com.amr.dkh_task.ui.adapters.FoodsAdapter
import com.amr.dkh_task.ui.adapters.FoodsAdapterBinder
import com.amr.dkh_task.ui.presenters.FoodsListContract
import com.amr.dkh_task.ui.presenters.FoodsListPresenter
import kotlinx.android.synthetic.main.fragment_foods.*
import javax.inject.Inject


class FoodsListFragment : BaseFragment(), FoodsAdapterBinder, FoodsListContract.View {


    @Inject
    @JvmField
    var foodsListPresenter: FoodsListPresenter? = null


    override fun setLoadingIndicatorVisible(loadingVisible: Boolean) {
        fragmentFoodsProgressBar?.visibility = if (loadingVisible) View.VISIBLE else View.GONE
    }

    override fun showFoodList(foodsList: MutableList<ItemsItem>) {
        this.foodsList = foodsList
        foodsAdapter.notifyDataSetChanged()

    }


    override fun showNoConnectivityError() {
        showSnack(fragmentFoodsContainer, R.string.no_connectivity, Snackbar.LENGTH_INDEFINITE, R.string.try_again, {
            foodsListPresenter?.getFoodsList()
        })
    }

    override fun showUnknownError() {
        showSnack(fragmentFoodsContainer, R.string.unknown_error, Snackbar.LENGTH_INDEFINITE, R.string.try_again, {
            foodsListPresenter?.getFoodsList()
        })

    }

    companion object {
        const val COLUMNS_COUNT = 2

        fun newInstance(): FoodsListFragment {
            val fragment = FoodsListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var foodsAdapter: FoodsAdapter

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var foodsList: MutableList<ItemsItem>

    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)

        foodsListPresenter?.attachView(this)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_foods, container, false)
            initView()
        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFoodRecyclerView?.layoutManager = mLayoutManager
        fragmentFoodRecyclerView?.adapter = foodsAdapter
    }

    override fun onResume() {
        super.onResume()
        showOrHideToolbarBack(false)
        foodsListPresenter?.getFoodsList()
    }


    private fun initView() {

        foodsList = mutableListOf()
        foodsAdapter = FoodsAdapter(this)
        mLayoutManager = GridLayoutManager(context, COLUMNS_COUNT)


    }

    override fun getFoodsList(): MutableList<ItemsItem> = foodsList

    override fun openFoodDetails(food: ItemsItem) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_container, FoodDetailsFragment.newInstance(food))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        foodsListPresenter?.detachView(this)
        foodsListPresenter?.destroy()
    }

    override fun onDestroy() {
        foodsListPresenter?.destroy()
        super.onDestroy()
    }
}
