package com.amr.dkh_task.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amr.dkh_task.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_food.view.*

class FoodsAdapter(private val foodsAdapterBinder: FoodsAdapterBinder) :
        RecyclerView.Adapter<FoodsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsAdapter.Holder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_food, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: FoodsAdapter.Holder, position: Int) {

        Glide.with(viewHolder.itemView.context)
                .load(foodsAdapterBinder.getFoodsList()[position].photoUrl)
                .into(viewHolder.foodImage)

        viewHolder.foodName?.text = foodsAdapterBinder.getFoodsList().get(position).name
        viewHolder.foodCardView.setOnClickListener(
                {
                    foodsAdapterBinder.openFoodDetails(foodsAdapterBinder
                            .getFoodsList()[position])
                })
    }

    override fun getItemCount(): Int = foodsAdapterBinder.getFoodsList().size


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var foodCardView = itemView.itemFoodCardView
        internal var foodImage = itemView.itemFoodImageView
        internal var foodName = itemView.itemFoodTxtViewName
    }
}
