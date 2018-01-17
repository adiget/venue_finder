package com.example.annada.eventfinder.adapters

import android.content.Context
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.annada.eventfinder.R

import com.example.annada.eventfinder.model.categoryapi.CategoryModel
import com.example.annada.eventfinder.views.PaletteTransformation
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.row_view.view.*


/**
 * Created by : annada
 * Date : 22/10/2017.
 */

class CategoryAdapter(private val mContext: Context, private val mCategories: List<CategoryModel>) : RecyclerView.Adapter<CategoryViewHolders>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolders {

        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return CategoryViewHolders(rowView)
    }

    override fun getItemCount() = mCategories.size

    override fun onBindViewHolder(holder: CategoryViewHolders, position: Int) {

        val selectedCategory = mCategories[position]

        holder.mCategoryId = selectedCategory.categoryId
        holder.itemView.venue_name!!.text = selectedCategory.categoryName
        Picasso.with(mContext)
                .load(selectedCategory.categoryIcon)
                .transform(PaletteTransformation.instance())
                //attempt to load the image from cache
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(holder.itemView.venue_icon, object : PaletteTransformation.PaletteCallback(holder.itemView.venue_icon!!) {
                    public override fun onSuccess(palette: Palette) {
                        val color = palette.getLightVibrantColor(
                                mContext.resources.getColor(android.R.color.holo_orange_light))
                        holder.itemView.venue_icon!!.setBackgroundColor(color)
                        selectedCategory.color = color
                    }

                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(mContext)
                                .load(selectedCategory.categoryIcon)
                                .into(holder.itemView.venue_icon, object : Callback {
                                    override fun onSuccess() {}

                                    override fun onError() {}
                                })
                    }
                })
    }
}
