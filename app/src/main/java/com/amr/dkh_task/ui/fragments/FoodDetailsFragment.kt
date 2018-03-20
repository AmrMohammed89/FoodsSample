package com.amr.dkh_task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amr.dkh_task.R
import com.amr.dkh_task.data.net.model.ItemsItem
import com.amr.dkh_task.ui.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragemnt_food_details.*


class FoodDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(food: ItemsItem): FoodDetailsFragment {
            val fragment = FoodDetailsFragment()
            val args = Bundle()
            args.putParcelable(Constants.FOOD, food)
            fragment.arguments = args
            return fragment
        }
    }

    private var rootView: View? = null
    private lateinit var foodItem: ItemsItem


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragemnt_food_details, container, false)

            foodItem = arguments.getParcelable(Constants.FOOD)

        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    //TODO : BASE FRAGMENT
    override fun onResume() {
        super.onResume()
        showOrHideToolbarBack(true)
    }

    private fun initView() {
        Glide.with(context)
                .load(foodItem.photoUrl)
                .into(fragmentFoodDetailsImageView)

        fragmentFoodDetailsTextViewName.text = foodItem.name
        fragmentFoodDetailsTextViewDescription.text = foodItem.description
    }
}
